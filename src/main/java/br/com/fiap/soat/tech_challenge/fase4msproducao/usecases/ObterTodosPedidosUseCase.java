package br.com.fiap.soat.tech_challenge.fase4msproducao.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterTodosPedidosUseCasePort;

@Component
public class ObterTodosPedidosUseCase implements ObterTodosPedidosUseCasePort {
    private final PedidoGatewayPort pedidoGateway;

    public ObterTodosPedidosUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> execute() {
        return pedidoGateway.obterTodosPedidos();
    }
}
