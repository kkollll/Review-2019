package myrabbitmq.rabbitmq.message;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import myrabbitmq.rabbitmq.utils.MyConnectionFactory;

public class Send {
	
	public static final String QUEUE_NAME = "hello mq!";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = MyConnectionFactory.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, "hello world".getBytes());
		channel.close();
		connection.close();
	}

}
