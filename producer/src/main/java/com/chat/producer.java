package com.chat;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

public class producer {
    private static final String QUEUE_NAME = "chat_fila";

    public static void main(String[] args) {
        var factory = new ConnectionFactory();
        factory.setHost("rabbitmq"); // ou "localhost", se for rodar local sem docker
        factory.setUsername("guest");
        factory.setPassword("guest");

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                Scanner scanner = new Scanner(System.in)
        ) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            System.out.println("Produtor iniciado. Digite mensagens ('sair' para encerrar):");
            while (true) {
                System.out.print("> ");
                var message = scanner.nextLine();
                if ("sair".equalsIgnoreCase(message)) break;

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Mensagem enviada: " + message);
            }
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }
}
