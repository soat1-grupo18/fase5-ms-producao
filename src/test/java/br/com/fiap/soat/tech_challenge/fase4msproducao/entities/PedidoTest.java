package br.com.fiap.soat.tech_challenge.fase4msproducao.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.builders.PedidoBuilder;
import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    void construtor_ComParametros_DeveInicializarCorretamente() {

        UUID id = UUID.randomUUID();
        UUID pedidoOriginalId = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();
        BigDecimal preco = BigDecimal.valueOf(100.0);
        StatusDoPedido statusDoPedido = StatusDoPedido.RECEBIDO;
        StatusDoPagamento statusDoPagamento = StatusDoPagamento.PENDENTE;
        List<ItemDoPedido> itens = new ArrayList<>();
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime dataDeCriacao = LocalDateTime.now();

        Pedido pedido = new Pedido(id, pedidoOriginalId, clienteId, preco, statusDoPedido, statusDoPagamento, itens, pagamentoId,
                dataDeCriacao);

        assertEquals(id, pedido.getId());
        assertEquals(pedidoOriginalId, pedido.getPedidoOriginalId());
        assertEquals(clienteId, pedido.getClienteId());
        assertEquals(preco, pedido.getPreco());
        assertEquals(statusDoPedido, pedido.getStatusDoPedido());
        assertEquals(statusDoPagamento, pedido.getStatusDoPagamento());
        assertEquals(itens, pedido.getItens());
        assertEquals(pagamentoId, pedido.getPagamentoId());
        assertEquals(dataDeCriacao, pedido.getDataDeCriacao());
    }

    @Test
    void adicionarItem_DeveAdicionarItemCorretamente() {

        Pedido pedido = PedidoBuilder.build();
        pedido.setPreco(BigDecimal.valueOf(0.0));
        ItemDoPedido item = new ItemDoPedido(UUID.randomUUID(), "Produto", "Descrição", "Categoria", "imagem.jpg", 1,
                BigDecimal.valueOf(10.0));

        pedido.adicionarItem(item);

        assertTrue(pedido.getItens().contains(item));
        assertEquals(BigDecimal.valueOf(10.0), pedido.getPreco());
    }

    @Test
    void getStatusDoPedido_DeveRetornarStatusCorreto() {

        Pedido pedido = PedidoBuilder.build();
        pedido.setStatusDoPedido(StatusDoPedido.RECEBIDO);

        assertEquals(StatusDoPedido.RECEBIDO, pedido.getStatusDoPedido());
    }

}
