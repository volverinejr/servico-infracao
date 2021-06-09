package br.com.claudemirojr.infracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicoInfracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoInfracaoApplication.class, args);
	}

}
