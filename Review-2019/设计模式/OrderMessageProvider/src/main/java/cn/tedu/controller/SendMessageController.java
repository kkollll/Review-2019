package cn.tedu.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.pojo.Order;

@RestController
public class SendMessageController {
	@Autowired
	AmqpTemplate amqpTemplate;

	@RequestMapping("/send")
	public String sendMessage() {
		// routingKey是队列名，用的是简单模式
		// 访问/send,进入rabbitmq后台，找到orderQueue
		// getMesssage
		String routingKey = "orderQueue3";
		Order pdOrder = new Order();
		pdOrder.setOrderId("20190710001");
		// 发到默认交换机
		amqpTemplate.convertAndSend(routingKey, pdOrder);
		return "ok2";
	}
}
