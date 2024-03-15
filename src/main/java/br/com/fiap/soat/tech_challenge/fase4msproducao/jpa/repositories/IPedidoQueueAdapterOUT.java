package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories;

public interface IPedidoQueueAdapterOUT {
    void publish(String message);
}
