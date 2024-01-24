package br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions;

import java.util.UUID;

public class PedidoNaoEncontradoException extends RuntimeException {

    PedidoNaoEncontradoException(String message) {
        super(message);
    };

    public static PedidoNaoEncontradoException aPartirDoId(UUID pedidoId) {
        return new PedidoNaoEncontradoException(String.format("O pedido de ID %s não foi encontrado.", pedidoId));
    }

    public static PedidoNaoEncontradoException aPartirDoPagamentoId(UUID pagamentoId) {
        return new PedidoNaoEncontradoException(String.format("O pedido com ID de pagamento %s não foi encontrado.", pagamentoId));
    }
}
