package com.example.SpringAssignment3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.SpringAssignment3.SpringAssignment3Application;

@Component
public class RabbitMQProducer {
	
	public static Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

	
	  @Value("${jsa.rabbitmq.exchange}")
	  private String exchange;
	  
	  @Value("${jsa.rabbitmq.routingkey}")
	  private String routingKey;

	 @Autowired
	 private AmqpTemplate amqpTemplate;
	 
	 
	 public void produceMsg(String msg){
		    amqpTemplate.convertAndSend(exchange, routingKey, msg);
		    logger.info("!!Message!!"+msg);
		  }
	  
	
}
