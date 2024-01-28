package br.com.fiap.soat.tech_challenge.fase4msproducao.api.requests;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoEmProducaoRequest {

    @NotNull
    private UUID pedidoId;

    @NotNull
    private UUID clienteId;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private List<ItemDoPedidoEmProducaoRequest> itens;

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

    public void setItens(List<ItemDoPedidoEmProducaoRequest> itens) {
        this.itens = itens;
    }

    public List<ItemDoPedidoEmProducaoRequest> getItens() {
        return itens;
    }


    public Pedido toDomain() {
        return new Pedido(
                null,
                pedidoId,
                clienteId,
                preco,
                StatusDoPedido.RECEBIDO,
                StatusDoPagamento.PENDENTE,
                itens.stream().map(ItemDoPedidoEmProducaoRequest::toDomain).collect(Collectors.toList()),
                null,
                LocalDateTime.now());
    }
}