package com.example.SpringAssignment3.controller;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JMSControllerTests {

	@Value("${inputTextFilePath}")
	private String INPUT_Text_FilePath;

	
	 @Test
	    public void testReadFileWithClassLoader(){
	        ClassLoader classLoader = this.getClass().getClassLoader();
	        File file = new File(classLoader.getResource(INPUT_Text_FilePath).getFile());
	        assertTrue(file.exists());
	 
	    }
	
	
	
}
