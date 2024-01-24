package br.com.fiap.soat.tech_challenge.fase4msproducao.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterPedidosPorStatusUseCasePort;

@Component
public class ObterPedidosPorStatusUseCase implements ObterPedidosPorStatusUseCasePort {

    private final PedidoGatewayPort pedidoGateway;

    public ObterPedidosPorStatusUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> execute(StatusDoPedido... statuses) {
        return pedidoGateway.obterPedidosPorStatus(statuses);
    }
}
