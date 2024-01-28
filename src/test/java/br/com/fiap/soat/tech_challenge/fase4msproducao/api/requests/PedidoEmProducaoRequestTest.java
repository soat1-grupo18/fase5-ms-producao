package br.com.fiap.soat.tech_challenge.fase4msproducao.api.requests;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoEmProducaoRequestTest {

    @Test
    void toDomain() {
        var request = new PedidoEmProducaoRequest();
        request.setPedidoId(UUID.randomUUID());
        request.setClienteId(UUID.randomUUID());
        request.setPreco(BigDecimal.valueOf(15));
        var item = new ItemDoPedidoEmProducaoRequest();
        item.setNome("nome");
        item.setDescricao("descricao");
        item.setCategoria("categoria");
        item.setImagem("imagem");
        item.setQuantidade(1);
        item.setPrecoUnitario(BigDecimal.valueOf(15));
        var itens = new ArrayList<ItemDoPedidoEmProducaoRequest>();
        itens.add(item);
        request.setItens(itens);

        var pedidoEmProducao = request.toDomain();

        assertEquals(request.getPedidoId(), pedidoEmProducao.getPedidoOriginalId());
        assertEquals(request.getClienteId(), pedidoEmProducao.getClienteId());
        assertEquals(request.getPreco(), pedidoEmProducao.getPreco());
        assertEquals(request.getItens().size(), pedidoEmProducao.getItens().size());
    }
}