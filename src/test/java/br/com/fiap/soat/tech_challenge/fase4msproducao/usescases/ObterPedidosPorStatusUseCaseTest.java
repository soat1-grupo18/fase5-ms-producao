package br.com.fiap.soat.tech_challenge.fase4msproducao.usescases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.usecases.ObterPedidosPorStatusUseCase;

public class ObterPedidosPorStatusUseCaseTest {

    @Test
    void execute_DeveRetornarListaDePedidosPorStatus() {

        StatusDoPedido status = StatusDoPedido.RECEBIDO;

        PedidoGatewayPort pedidoGatewayMock = Mockito.mock(PedidoGatewayPort.class);

        Pedido pedido1 = new Pedido(UUID.randomUUID(), StatusDoPedido.RECEBIDO);
        Pedido pedido2 = new Pedido(UUID.randomUUID(), StatusDoPedido.EM_PREPARACAO);
        List<Pedido> pedidosSimulados = Arrays.asList(pedido1, pedido2);

        when(pedidoGatewayMock.obterPedidosPorStatus(status)).thenReturn(pedidosSimulados);

        ObterPedidosPorStatusUseCase useCase = new ObterPedidosPorStatusUseCase(pedidoGatewayMock);

        List<Pedido> pedidos = useCase.execute(status);

        assertEquals(pedidosSimulados, pedidos);
    }
}
