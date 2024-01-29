package br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases;

import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public interface AtualizarStatusPedidoUseCasePort {
    Pedido execute(UUID pedidoOriginalId, StatusDoPedido statusDoPedido);
}
