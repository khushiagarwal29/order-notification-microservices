package com.example.OrderService.client;


import com.example.OrderService.dto.NotificationRequest;
import com.example.OrderService.exception.NotificationServiceException;
import com.example.OrderService.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NotificationClient {

    private final WebClient webClient;

    public NotificationClient(WebClient.Builder builder,
                              @Value("${notification-service.base-url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public void sendOrderCreatedNotification(Order order) {
        NotificationRequest req = new NotificationRequest(
                order.getCustomerName(),
                "ORDER_CREATED",
                "Your order for %s has been placed.".formatted(order.getProduct())
        );

        webClient.post()
                .uri("/api/notifications")
                .bodyValue(req)
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp ->
                        Mono.error(new NotificationServiceException("Notification call failed: " + resp.statusCode())))
                .toBodilessEntity()
                .block(); // synchronous on purpose — this is the "before" version
    }
}