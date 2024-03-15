package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories;

import org.springframework.messaging.handler.annotation.Payload;

public interface IpedidoQueueAdapterIN {

    void receive(@Payload String message);
}
