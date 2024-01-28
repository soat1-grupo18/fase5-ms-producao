package br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions;

import java.util.UUID;

public class PedidoNaoEncontradoException extends RuntimeException {

    PedidoNaoEncontradoException(String message) {
        super(message);
    };

    public static PedidoNaoEncontradoException aPartirDoId(UUID pedidoId) {
        return new PedidoNaoEncontradoException(String.format("O pedido de ID %s não foi encontrado.", pedidoId));
    }
}
