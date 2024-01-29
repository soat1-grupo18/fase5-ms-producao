package br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public interface PedidoGatewayPort {
    Pedido inserirPedido(Pedido pedido);

    Pedido obterPedido(UUID pedidoOriginalId);

    List<Pedido> obterTodosPedidos();

    Boolean consultarStatusPagamento(UUID pedidoId);

    void atualizarPedido(Pedido pedido);

    Optional<Pedido> obterPedidoComPagamentoId(UUID pagamentoId);

    List<Pedido> obterPedidosPorStatus(StatusDoPedido... statuses);
}
