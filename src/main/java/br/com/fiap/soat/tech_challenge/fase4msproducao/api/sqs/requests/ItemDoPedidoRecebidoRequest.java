package br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs.requests;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;

import java.math.BigDecimal;

public class ItemDoPedidoRecebidoRequest {
    private String nome;
    private String descricao;
    private String categoria;
    private String imagem;
    private int quantidade;
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

    public static ItemDoPedido toDomain(ItemDoPedidoRecebidoRequest itemDoPedidoRecebidoRequest) {
        return new ItemDoPedido(
                null,
                itemDoPedidoRecebidoRequest.getNome(),
                itemDoPedidoRecebidoRequest.getDescricao(),
                itemDoPedidoRecebidoRequest.getCategoria(),
                itemDoPedidoRecebidoRequest.getImagem(),
                itemDoPedidoRecebidoRequest.getQuantidade(),
                itemDoPedidoRecebidoRequest.getPrecoUnitario()
        );
    }

    @Override
    public String toString() {
        return "ItemDoPedidoRecebidoRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", imagem='" + imagem + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
