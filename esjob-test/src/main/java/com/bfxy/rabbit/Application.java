package com.bfxy.rabbit;

import com.mercurius.task.annotation.EnableElasticJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableElasticJob
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
