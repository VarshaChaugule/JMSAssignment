package com.example.SpringAssignment3.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringAssignment3.SpringAssignment3Application;
import com.example.SpringAssignment3.service.RabbitMQProducer;

@RestController
public class JMSController {

	public static Logger logger = LoggerFactory.getLogger(JMSController.class);

	@Value("${inputTextFilePath}")
	private String INPUT_Text_FilePath;

	@Autowired
	RabbitMQProducer producer;

	
	@Scheduled(cron="${scheduling.cron}")
	@RequestMapping(path = "/send")
	public String sendMsg() throws IOException {
		
		logger.info("In Send Message Method");
		
		File file = new File(INPUT_Text_FilePath);

		FileInputStream fin = new FileInputStream(file);
		byte[] fileData = new byte[(int) file.length()];
		fin.read(fileData);
		String fileType = new String(fileData);
		producer.produceMsg(fileType);
		return "Done";
	}
}
