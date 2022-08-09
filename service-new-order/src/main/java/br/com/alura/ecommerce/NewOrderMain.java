package br.com.alura.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaDispatcher orderDispatcher = new KafkaDispatcher<Order>();
        KafkaDispatcher emailDispatcher = new KafkaDispatcher<String>();

        String userId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();
        BigDecimal amount = new BigDecimal(Math.random() * 5000 + 1);
        Order order = new Order(userId, orderId, amount);
        orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

        String email = "Welcome! We are processing your order";
        emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);

    }


}
