package com.farmu.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableJpaAuditing
//@EnableFeignClients
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableRetry
public class FarmuExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmuExampleApplication.class, args);
	}

}
