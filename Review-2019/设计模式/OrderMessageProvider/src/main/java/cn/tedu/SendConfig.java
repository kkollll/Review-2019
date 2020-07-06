package cn.tedu;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//连接redis用的框架是jedis
//连接solr用的框架是solrJ
//连接rabbitmq用的框架是amqp

//消息队列框架amqp的配置信息
//没用springboot之前配置信息一般放在.xml中
//mybatis-config.xml 配置sqlsessionFactoryBean

//用springboot之后，在一个class完成配置
//注册一个队列对象到spring框架
@Configuration
public class SendConfig {
//创建一个Queue对象放到spring容器中
	//有两种方法
	//1, .xml <bean>
	//2,@bean
	//org.springframework.amqp.core.Queue
	@Bean
	public Queue initQueue()
	{
		//队列名称orderQueue在服务器上不存在。
		Queue queue=new Queue("orderQueue3");
		return queue;
	}
}







