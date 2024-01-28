package br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;

public class StatusPedidoNaoAtualizadoException extends RuntimeException {
    StatusPedidoNaoAtualizadoException(String message) { super(message); }

    public static StatusPedidoNaoAtualizadoException porProximoStatusInvalido(StatusDoPedido statusAnterior, StatusDoPedido statusProximo) {
        return new StatusPedidoNaoAtualizadoException(String.format("Não é possível atualizar para o status %s um pedido com status %s.", statusProximo, statusAnterior));
    }
}
