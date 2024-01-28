package br.com.fiap.soat.tech_challenge.fase4msproducao.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions.StatusPedidoNaoAtualizadoException;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.AtualizarStatusPedidoUseCasePort;

@Component
public class AtualizarStatusPedidoUseCase implements AtualizarStatusPedidoUseCasePort {

    private final PedidoGatewayPort pedidoGateway;

    public AtualizarStatusPedidoUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido execute(UUID pedidoId, StatusDoPedido statusDoPedido) {
        var pedido = pedidoGateway.obterPedido(pedidoId);

        if (!pedido.getStatusDoPedido().podeAtualizarPara(statusDoPedido)) {
            throw StatusPedidoNaoAtualizadoException.porProximoStatusInvalido(pedido.getStatusDoPedido(),
                    statusDoPedido);
        }

        pedido.setStatusDoPedido(statusDoPedido);
        pedidoGateway.atualizarPedido(pedido);
        return pedido;
    }
}
