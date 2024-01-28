package br.com.fiap.soat.tech_challenge.fase4msproducao.api.requests;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemDoPedidoEmProducaoRequestTest {

    @Test
    void toDomain() {
        var item = new ItemDoPedidoEmProducaoRequest();
        item.setNome("nome");
        item.setDescricao("descricao");
        item.setCategoria("categoria");
        item.setImagem("imagem");
        item.setQuantidade(1);
        item.setPrecoUnitario(BigDecimal.valueOf(15));

        var itemDoPedido = item.toDomain();

        assertEquals(item.getNome(), itemDoPedido.getNome());
        assertEquals(item.getDescricao(), itemDoPedido.getDescricao());
        assertEquals(item.getCategoria(), itemDoPedido.getCategoria());
        assertEquals(item.getImagem(), itemDoPedido.getImagem());
        assertEquals(item.getQuantidade(), itemDoPedido.getQuantidade());
        assertEquals(item.getPrecoUnitario(), itemDoPedido.getPrecoUnitario());
    }
}