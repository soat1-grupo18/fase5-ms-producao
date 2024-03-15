package br.com.fiap.soat.tech_challenge.fase4msproducao;

import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.messaging.EventQueuesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EventQueuesProperties.class)
public class Fase4MsProducaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(Fase4MsProducaoApplication.class, args);
	}
}


