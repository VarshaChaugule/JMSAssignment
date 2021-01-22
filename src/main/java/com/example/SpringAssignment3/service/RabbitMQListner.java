package com.example.SpringAssignment3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import com.example.SpringAssignment3.configuration.RabbitMQConfig;

@Service
public class RabbitMQListner implements MessageListener {

	public static Logger logger = LoggerFactory.getLogger(RabbitMQListner.class);
	
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		logger.info("Consuming Message - "+ new String(message.getBody()));
		
	}

}
