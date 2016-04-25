package com.tgt.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tgt.app.ProductRepository;
/**
 * @author BonyThomas:
 * @Description: This is a  Main class of My Restful Web service 
 */
@SpringBootApplication
public class TargetCaseStudyApplication {
	
	/*
     * @Description: Main function that get executed first During application launch
     * @param: none
     * @return: none 
     */
	public static void main(String[] args) {
		SpringApplication.run(TargetCaseStudyApplication.class, args);
	}
}
