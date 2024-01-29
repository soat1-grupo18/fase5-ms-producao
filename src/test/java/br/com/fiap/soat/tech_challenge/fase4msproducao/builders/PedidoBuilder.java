package br.com.fiap.soat.tech_challenge.fase4msproducao.builders;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class PedidoBuilder {
    public static Pedido build() {
        return new Pedido (UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), BigDecimal.valueOf(21), StatusDoPedido.EM_PREPARACAO, StatusDoPagamento.PENDENTE, new ArrayList<>(), null, LocalDateTime.now());
    }
}
