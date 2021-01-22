package com.example.SpringAssignment3;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class SpringAssignment3Application {

	
	public static Logger logger= LoggerFactory.getLogger(SpringAssignment3Application.class);

	
	public static void main(String[] args) {
		SpringApplication.run(SpringAssignment3Application.class, args);
	}
	
}
