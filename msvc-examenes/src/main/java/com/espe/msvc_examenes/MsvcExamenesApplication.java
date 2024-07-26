package com.espe.msvc_examenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
// clase principal de la aplicación
public class MsvcExamenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcExamenesApplication.class, args);
	}

}
