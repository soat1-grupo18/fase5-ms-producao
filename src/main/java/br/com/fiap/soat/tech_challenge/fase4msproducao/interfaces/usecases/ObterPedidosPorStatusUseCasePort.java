package br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases;

import java.util.List;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public interface ObterPedidosPorStatusUseCasePort {
    List<Pedido> execute(StatusDoPedido... statuses);
}
