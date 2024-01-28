package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;

public class ItemDoPedidoJpaEntityTest {

    @Test
    void testToDomain() {

        UUID id = UUID.randomUUID();
        String nome = "Item Teste";
        String descricao = "Descrição do Item Teste";
        String categoria = "Categoria Teste";
        String imagem = "imagem_teste.png";
        int quantidade = 2;
        BigDecimal precoUnitario = BigDecimal.valueOf(10.0);

        ItemDoPedido itemDoPedido = new ItemDoPedido(id, nome, descricao, categoria, imagem, quantidade, precoUnitario);

        ItemDoPedidoJpaEntity itemEntity = new ItemDoPedidoJpaEntity();
        itemEntity.setId(id);
        itemEntity.setNome(nome);
        itemEntity.setDescricao(descricao);
        itemEntity.setCategoria(categoria);
        itemEntity.setImagem(imagem);
        itemEntity.setQuantidade(quantidade);
        itemEntity.setPrecoUnitario(precoUnitario);

        ItemDoPedido result = itemEntity.toDomain();

        assertEquals(id, result.getId());
        assertEquals(nome, result.getNome());
        assertEquals(descricao, result.getDescricao());
        assertEquals(categoria, result.getCategoria());
        assertEquals(imagem, result.getImagem());
        assertEquals(quantidade, result.getQuantidade());
        assertEquals(precoUnitario, result.getPrecoUnitario());
    }

    @Test
    void testFromDomain() {

        UUID id = UUID.randomUUID();
        String nome = "Item Teste";
        String descricao = "Descrição do Item Teste";
        String categoria = "Categoria Teste";
        String imagem = "imagem_teste.png";
        int quantidade = 2;
        BigDecimal precoUnitario = BigDecimal.valueOf(10.0);

        ItemDoPedido itemDoPedido = new ItemDoPedido(id, nome, descricao, categoria, imagem, quantidade, precoUnitario);

        ItemDoPedidoJpaEntity result = ItemDoPedidoJpaEntity.fromDomain(itemDoPedido);

        assertEquals(id, result.getId());
        assertEquals(nome, result.getNome());
        assertEquals(descricao, result.getDescricao());
        assertEquals(categoria, result.getCategoria());
        assertEquals(imagem, result.getImagem());
        assertEquals(quantidade, result.getQuantidade());
        assertEquals(precoUnitario, result.getPrecoUnitario());
    }

}
