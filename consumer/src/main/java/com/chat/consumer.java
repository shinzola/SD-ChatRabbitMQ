package com.chat;

import com.rabbitmq.client.*;

public class consumer {
    private static final String QUEUE_NAME = "chat_fila";

    public static void main(String[] args) {
        var factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setUsername("guest");
        factory.setPassword("guest");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println("Consumidor aguardando mensagens...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                var message = new String(delivery.getBody());
                System.out.println("Mensagem recebida: " + message);

                try {
                    Thread.sleep(500); // simula processamento
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // boa prática
                    System.err.println("Thread interrompida: " + e.getMessage());
                }

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.println("Mensagem confirmada com ack.");
            };

            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            System.err.println("Erro ao consumir mensagem: " + e.getMessage());
        }
    }
}
