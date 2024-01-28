package br.com.fiap.soat.tech_challenge.fase4msproducao.usecases;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.CriarPedidoEmProducaoUseCasePort;
import org.springframework.stereotype.Component;

@Component
public class CriarPedidoEmProducaoUseCase implements CriarPedidoEmProducaoUseCasePort {

    private final PedidoGatewayPort pedidoGateway;

    public CriarPedidoEmProducaoUseCase(PedidoGatewayPort pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido execute(Pedido pedido) {
        return pedidoGateway.inserirPedido(pedido);
    }
}
