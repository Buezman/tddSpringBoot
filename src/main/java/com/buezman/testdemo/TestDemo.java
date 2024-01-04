package com.buezman.testdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestDemo {

	public static void main(String[] args) {
		SpringApplication.run(TestDemo.class, args);
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

}
