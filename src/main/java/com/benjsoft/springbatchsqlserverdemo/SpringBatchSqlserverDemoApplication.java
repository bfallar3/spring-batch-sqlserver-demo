package com.benjsoft.springbatchsqlserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBatchSqlserverDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchSqlserverDemoApplication.class, args);
	}

}
