package br.com.fiap.soat.tech_challenge.fase4msproducao.api.requests;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemDoPedidoEmProducaoRequest {
    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private String categoria;

    @NotNull
    private String imagem;

    @NotNull
    private int quantidade;

    @NotNull
    private BigDecimal precoUnitario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public ItemDoPedido toDomain() {
        return new ItemDoPedido(
                null,
                nome,
                descricao,
                categoria,
                imagem,
                quantidade,
                precoUnitario);
    }
}
