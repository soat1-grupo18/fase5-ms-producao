package br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;

@Component
public interface ObterTodosPedidosUseCasePort {
    List<Pedido> execute();
}
