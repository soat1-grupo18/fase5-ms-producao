package br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;

public interface CriarPedidoEmProducaoUseCasePort {
    Pedido execute(Pedido pedido);
}
