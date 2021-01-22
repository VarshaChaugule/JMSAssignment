package com.example.SpringAssignment3.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import com.example.SpringAssignment3.controller.JMSController;
import com.example.SpringAssignment3.service.RabbitMQListner;


@Configuration
@EnableJms
@ComponentScan(basePackages = "com.example.SpringAssignment3")
public class RabbitMQConfig {
	
	public static Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
	
	@Value("${spring.rabbitmq.queue}")
	String queueName;

	@Value("${spring.rabbitmq.host}")
	private String HOST;
	
	@Value("${spring.rabbitmq.port}")
	private int PORT;
	
	@Value("${spring.rabbitmq.username}")
	private String USERNAME;
	
	@Value("${spring.rabbitmq.password}")
	private String PASSWORD;
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername(USERNAME);
		cachingConnectionFactory.setPassword(PASSWORD);
		logger.info("Caching Connection Factory");
		return cachingConnectionFactory;
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setConnectionFactory(connectionFactory);
		logger.info("!!Connection Established!!");
	    return rabbitTemplate;
	}

	@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
		logger.info("In Message Listener");
		return simpleMessageListenerContainer;
		
	}
}	



	

