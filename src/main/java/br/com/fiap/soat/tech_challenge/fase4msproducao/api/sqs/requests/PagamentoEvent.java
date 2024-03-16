package br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs.requests;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PagamentoEvent {
    private String pagamentoId;
    private String pedidoId;
    private String clienteId;
    private BigDecimal total;
    private String status;

    public String getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(String pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
