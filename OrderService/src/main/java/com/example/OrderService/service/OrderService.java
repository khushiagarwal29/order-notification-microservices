package com.example.OrderService.service;

import com.example.OrderService.dto.OrderRequest;
import com.example.OrderService.dto.OrderResponse;
import com.example.OrderService.event.OrderCreatedEvent;
import com.example.OrderService.exception.OrderNotFoundException;
import com.example.OrderService.kafka.OrderEventProducer;
import com.example.OrderService.model.Order;
import com.example.OrderService.model.OrderStatus;
import com.example.OrderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    @Transactional
    public OrderResponse createOrder(OrderRequest req) {
        Order order = new Order();
        order.setCustomerName(req.customerName());
        order.setProduct(req.product());
        order.setQuantity(req.quantity());
        order.setTotalPrice(req.totalPrice());
        log.info("Saving order into PostgreSQL...");
        order = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getCustomerName(),
                order.getProduct(),
                order.getQuantity(),
                order.getTotalPrice()
        );

        orderEventProducer.publish(event);

        order.setStatus(OrderStatus.NOTIFIED);

        orderRepository.save(order);

        return toResponse(order);
    }

    public OrderResponse getOrder(UUID id) {
        return orderRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<OrderResponse> listOrders() {
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    private OrderResponse toResponse(Order o) {
        return new OrderResponse(o.getId(), o.getCustomerName(), o.getProduct(),
                o.getQuantity(), o.getTotalPrice(), o.getStatus().name(), o.getCreatedAt());
    }
}

