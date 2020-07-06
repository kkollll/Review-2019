package myrabbitmq.rabbitmq.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConnectionFactory;

public class MyConnectionFactory {
	
	public static com.rabbitmq.client.Connection getConnection() throws IOException, TimeoutException {
	 com.rabbitmq.client.ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.116.131");
		factory.setPort(5672);
		factory.setVirtualHost("/");
		factory.setUsername("admin");
		factory.setPassword("123");
		return factory.newConnection();
	}
}
