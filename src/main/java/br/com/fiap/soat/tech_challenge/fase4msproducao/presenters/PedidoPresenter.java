package br.com.fiap.soat.tech_challenge.fase4msproducao.presenters;


import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public class PedidoPresenter {
    private UUID id;
    private StatusDoPedido statusDoPedido;
    private UUID pedidoOriginalId;

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

        return pedidoPresenter;
    }

    public UUID getPedidoOriginalId() {
        return pedidoOriginalId;
    }
}
