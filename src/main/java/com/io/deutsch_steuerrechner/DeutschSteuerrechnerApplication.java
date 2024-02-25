package com.io.deutsch_steuerrechner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.io.deutsch_steuerrechner.service.TelegramBot;

@SpringBootApplication
public class DeutschSteuerrechnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeutschSteuerrechnerApplication.class, args);
	}

}
