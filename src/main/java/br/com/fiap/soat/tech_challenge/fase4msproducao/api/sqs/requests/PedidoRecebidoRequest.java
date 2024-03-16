package br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs.requests;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoRecebidoRequest {
    private UUID pedidoId;
    private UUID clienteId;
    private BigDecimal preco;
    private List<ItemDoPedidoRecebidoRequest> itens;

    public Pedido toDomain() {
        UUID pedidoId = this.getPedidoId();
        UUID clienteId = this.getClienteId();
        BigDecimal preco = this.getPreco();
        StatusDoPedido statusDoPedido = StatusDoPedido.RECEBIDO;
        StatusDoPagamento statusDoPagamento = StatusDoPagamento.PENDENTE;
        List<ItemDoPedido> itens = this.itens.stream().map(ItemDoPedidoRecebidoRequest::toDomain).collect(Collectors.toList());
        UUID pagamentoId = null;
        LocalDateTime dataDeCriacao = LocalDateTime.now();

        return new Pedido(pedidoId, null, clienteId, preco, statusDoPedido, statusDoPagamento, itens, pagamentoId, dataDeCriacao);
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public UUID getClienteId() {
        return clienteId;
    }

    public void setClienteId(UUID clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<ItemDoPedidoRecebidoRequest> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedidoRecebidoRequest> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "PedidoRecebidoRequest{" +
                "pedidoId=" + pedidoId +
                ", clienteId=" + clienteId +
                ", preco=" + preco +
                ", itens=" + itens +
                '}';
    }
}
