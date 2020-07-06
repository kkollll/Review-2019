package myrabbitmq.rabbitmq.message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import myrabbitmq.rabbitmq.utils.MyConnectionFactory;

public class Recv {
	public static final String QUEUE_NAME = "hello mq!";
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = MyConnectionFactory.getConnection();
		Channel channel = connection.createChannel();
		DeliverCallback deliveryCallback =(consumerTag, delivery)-> {
			String message = new String(delivery.getBody(), "UTF-8");
		    System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(QUEUE_NAME, true, deliveryCallback, consumerTag -> { });

	}

}
