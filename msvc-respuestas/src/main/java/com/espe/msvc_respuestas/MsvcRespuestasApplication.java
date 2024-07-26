package com.espe.msvc_respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcRespuestasApplication.class, args);
	}

}
