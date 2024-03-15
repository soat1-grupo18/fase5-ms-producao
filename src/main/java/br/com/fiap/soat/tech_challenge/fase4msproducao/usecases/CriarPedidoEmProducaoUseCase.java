package br.com.fiap.soat.tech_challenge.fase4msproducao.usecases;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.CriarPedidoEmProducaoUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.IPedidoQueueAdapterOUT;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


@Component
public class CriarPedidoEmProducaoUseCase implements CriarPedidoEmProducaoUseCasePort {

    private final PedidoGatewayPort pedidoGateway;
    private final IPedidoQueueAdapterOUT pedidoQueueAdapterOUT;

    @Autowired
    private Gson gson;

    public CriarPedidoEmProducaoUseCase(PedidoGatewayPort pedidoGateway, IPedidoQueueAdapterOUT pedidoQueueAdapterOUT) {
        this.pedidoGateway = pedidoGateway;
        this.pedidoQueueAdapterOUT = pedidoQueueAdapterOUT;
    }


    private String toPedidoMessage(Pedido pedido){
    Map message = new HashMap<String, String>();
    message.put("codigoPedido",pedido.getPedidoOriginalId());
    message.put("precoTotal",pedido.getPreco());
    message.put("codigoCliente",pedido.getClienteId());
    message.put("statusDoPagamento", pedido.getStatusDoPagamento());
    message.put("statusDoPedido", pedido.getStatusDoPedido());
    return gson.toJson(message);
    }

    public Pedido execute(Pedido pedido) {

        pedidoQueueAdapterOUT.publish(toPedidoMessage(pedido));
        return pedidoGateway.inserirPedido(pedido);

    }

}