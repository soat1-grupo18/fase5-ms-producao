package br.com.fiap.soat.tech_challenge.fase4msproducao.presenters;


import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public class PedidoPresenter {
    private UUID id;
    private StatusDoPedido statusDoPedido;
    private UUID pedidoOriginalId;
    private UUID clienteId;

    private StatusDoPagamento statusDoPagamento;

    public UUID getId() {
        return id;
    }

    public StatusDoPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public static PedidoPresenter fromDomain(Pedido pedido) {
        PedidoPresenter pedidoPresenter = new PedidoPresenter();

        pedidoPresenter.id = pedido.getId();
        pedidoPresenter.pedidoOriginalId = pedido.getPedidoOriginalId();
        pedidoPresenter.statusDoPedido = pedido.getStatusDoPedido();
        pedidoPresenter.clienteId = pedido.getClienteId();
        pedidoPresenter.statusDoPagamento = pedido.getStatusDoPagamento();

        return pedidoPresenter;
    }

    public UUID getPedidoOriginalId() {
        return pedidoOriginalId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public StatusDoPagamento getStatusDoPagamento() {
        return statusDoPagamento;
    }
}
