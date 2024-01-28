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
import br.com.fiap.soat.tech_challenge.fase4msproducao.usecases.ObterTodosPedidosUseCase;

public class ObterTodosPedidosUseCaseTest {

    @Test
    void execute_DeveRetornarListaDeTodosPedidos() {

        PedidoGatewayPort pedidoGatewayMock = Mockito.mock(PedidoGatewayPort.class);

        Pedido pedido1 = new Pedido(UUID.randomUUID(), StatusDoPedido.RECEBIDO);
        Pedido pedido2 = new Pedido(UUID.randomUUID(), StatusDoPedido.EM_PREPARACAO);
        List<Pedido> pedidosSimulados = Arrays.asList(pedido1, pedido2);

        when(pedidoGatewayMock.obterTodosPedidos()).thenReturn(pedidosSimulados);

        ObterTodosPedidosUseCase useCase = new ObterTodosPedidosUseCase(pedidoGatewayMock);

        List<Pedido> pedidos = useCase.execute();

        assertEquals(pedidosSimulados, pedidos);
    }
}
