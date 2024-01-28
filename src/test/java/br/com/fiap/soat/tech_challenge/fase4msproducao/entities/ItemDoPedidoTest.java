package br.com.fiap.soat.tech_challenge.fase4msproducao.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class ItemDoPedidoTest {

    @Test
    void construtor_DeveInicializarCorretamente() {

        UUID id = UUID.randomUUID();
        String nome = "Produto";
        String descricao = "Descrição do produto";
        String categoria = "Categoria";
        String imagem = "imagem.jpg";
        int quantidade = 10;
        BigDecimal precoUnitario = BigDecimal.valueOf(100.0);

        ItemDoPedido itemDoPedido = new ItemDoPedido(id, nome, descricao, categoria, imagem, quantidade, precoUnitario);

        assertEquals(id, itemDoPedido.getId());
        assertEquals(nome, itemDoPedido.getNome());
        assertEquals(descricao, itemDoPedido.getDescricao());
        assertEquals(categoria, itemDoPedido.getCategoria());
        assertEquals(imagem, itemDoPedido.getImagem());
        assertEquals(quantidade, itemDoPedido.getQuantidade());
        assertEquals(precoUnitario, itemDoPedido.getPrecoUnitario());
    }

    @Test
    void setCategoria_DeveAtualizarCategoriaCorretamente() {

        ItemDoPedido itemDoPedido = new ItemDoPedido(UUID.randomUUID(), "Produto", "Descrição", "Categoria Antiga",
                "imagem.jpg", 1, BigDecimal.valueOf(10.0));
        String novaCategoria = "Nova Categoria";

        itemDoPedido.setCategoria(novaCategoria);

        assertEquals(novaCategoria, itemDoPedido.getCategoria());
    }

    @Test
    void setImagem_DeveAtualizarImagemCorretamente() {
        ItemDoPedido itemDoPedido = new ItemDoPedido(UUID.randomUUID(), "Produto", "Descrição", "Categoria",
                "imagem_antiga.jpg", 1, BigDecimal.valueOf(10.0));
        String novaImagem = "nova_imagem.jpg";

        itemDoPedido.setImagem(novaImagem);

        assertEquals(novaImagem, itemDoPedido.getImagem());
    }
}
