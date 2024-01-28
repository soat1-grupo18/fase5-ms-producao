package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public class PedidoJpaEntityTest {

    @Test
    void testToDomain() {

        ItemDoPedidoJpaEntity itemEntity1 = mock(ItemDoPedidoJpaEntity.class);
        ItemDoPedidoJpaEntity itemEntity2 = mock(ItemDoPedidoJpaEntity.class);
        List<ItemDoPedidoJpaEntity> itensEntity = new ArrayList<>();
        itensEntity.add(itemEntity1);
        itensEntity.add(itemEntity2);

        ItemDoPedido item1 = new ItemDoPedido(UUID.randomUUID(), "Item 1", "Descrição do Item 1", "Categoria 1",
                "imagem1.png", 1, BigDecimal.TEN);
        ItemDoPedido item2 = new ItemDoPedido(UUID.randomUUID(), "Item 2", "Descrição do Item 2", "Categoria 2",
                "imagem2.png", 2, BigDecimal.valueOf(20.0));
        Mockito.when(itemEntity1.toDomain()).thenReturn(item1);
        Mockito.when(itemEntity2.toDomain()).thenReturn(item2);

        UUID id = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();
        BigDecimal preco = BigDecimal.valueOf(30.0);
        StatusDoPedido statusDoPedido = StatusDoPedido.RECEBIDO;
        StatusDoPagamento statusDoPagamento = StatusDoPagamento.PENDENTE;
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime dataDeCriacao = LocalDateTime.now();
        PedidoJpaEntity pedidoEntity = new PedidoJpaEntity();
        pedidoEntity.setId(id);
        pedidoEntity.setClienteId(clienteId);
        pedidoEntity.setPreco(preco);
        pedidoEntity.setStatusDoPedido(statusDoPedido);
        pedidoEntity.setStatusDoPagamento(statusDoPagamento);
        pedidoEntity.setPagamentoId(pagamentoId);
        pedidoEntity.setDataDeCriacao(dataDeCriacao);
        pedidoEntity.setItens(itensEntity);

        Pedido result = pedidoEntity.toDomain();

        assertEquals(id, result.getId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(preco, result.getPreco());
        assertEquals(statusDoPedido, result.getStatusDoPedido());
        assertEquals(statusDoPagamento, result.getStatusDoPagamento());
        assertEquals(pagamentoId, result.getPagamentoId());
        assertEquals(dataDeCriacao, result.getDataDeCriacao());
        assertEquals(2, result.getItens().size());
        assertEquals(item1, result.getItens().get(0));
        assertEquals(item2, result.getItens().get(1));
    }

    @Test
    void testFromDomain() {

        ItemDoPedido item1 = new ItemDoPedido(UUID.randomUUID(), "Item 1", "Descrição do Item 1", "Categoria 1",
                "imagem1.png", 1, BigDecimal.TEN);
        ItemDoPedido item2 = new ItemDoPedido(UUID.randomUUID(), "Item 2", "Descrição do Item 2", "Categoria 2",
                "imagem2.png", 2, BigDecimal.valueOf(20.0));
        List<ItemDoPedido> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);

        UUID id = UUID.randomUUID();
        UUID clienteId = UUID.randomUUID();
        BigDecimal preco = BigDecimal.valueOf(30.0);
        StatusDoPedido statusDoPedido = StatusDoPedido.RECEBIDO;
        StatusDoPagamento statusDoPagamento = StatusDoPagamento.PENDENTE;
        UUID pagamentoId = UUID.randomUUID();
        LocalDateTime dataDeCriacao = LocalDateTime.now();
        Pedido pedido = new Pedido(id, clienteId, preco, statusDoPedido, statusDoPagamento, itens, pagamentoId,
                dataDeCriacao);

        PedidoJpaEntity result = PedidoJpaEntity.fromDomain(pedido);

        assertEquals(id, result.getId());
        assertEquals(clienteId, result.getClienteId());
        assertEquals(preco, result.getPreco());
        assertEquals(statusDoPedido, result.getStatusDoPedido());
        assertEquals(statusDoPagamento, result.getStatusDoPagamento());
        assertEquals(pagamentoId, result.getPagamentoId());
        assertEquals(dataDeCriacao, result.getDataDeCriacao());
        assertEquals(2, result.getItens().size());

        assertEquals(item1.getId(), result.getItens().get(0).getId());
        assertEquals(item1.getNome(), result.getItens().get(0).getNome());
        assertEquals(item1.getDescricao(), result.getItens().get(0).getDescricao());
        assertEquals(item1.getCategoria(), result.getItens().get(0).getCategoria());
        assertEquals(item1.getImagem(), result.getItens().get(0).getImagem());
        assertEquals(item1.getQuantidade(), result.getItens().get(0).getQuantidade());
        assertEquals(item1.getPrecoUnitario(), result.getItens().get(0).getPrecoUnitario());
        assertEquals(item2.getId(), result.getItens().get(1).getId());
        assertEquals(item2.getNome(), result.getItens().get(1).getNome());
        assertEquals(item2.getDescricao(), result.getItens().get(1).getDescricao());
        assertEquals(item2.getCategoria(), result.getItens().get(1).getCategoria());
        assertEquals(item2.getImagem(), result.getItens().get(1).getImagem());
        assertEquals(item2.getQuantidade(), result.getItens().get(1).getQuantidade());
        assertEquals(item2.getPrecoUnitario(), result.getItens().get(1).getPrecoUnitario());
    }
}
