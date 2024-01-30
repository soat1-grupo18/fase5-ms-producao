package br.com.fiap.soat.tech_challenge.fase4msproducao.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.builders.PedidoBuilder;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.AtualizarStatusPedidoUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterPedidosPorStatusUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterTodosPedidosUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.presenters.PedidoPresenter;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock
    private ObterTodosPedidosUseCasePort obterTodosPedidosUseCase;

    @Mock
    private ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase;

    @Mock
    private AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    void aoObterTodosPedidos_DeveRetornarListaDePedidos() {

        Pedido pedido1 = PedidoBuilder.build();
        Pedido pedido2 = PedidoBuilder.build();
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(obterTodosPedidosUseCase.execute()).thenReturn(pedidos);

        List<PedidoPresenter> resultado = pedidoController.obterTodosPedidos();

        verify(obterTodosPedidosUseCase, times(1)).execute();

        assertEquals(2, resultado.size());

    }

    @Test
    void aoObterPedidosPorStatus_DeveRetornarListaFiltradaPorStatus() {
        Pedido pedido1 = PedidoBuilder.build();
        Pedido pedido2 = PedidoBuilder.build();
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(obterPedidosPorStatusUseCase.execute(StatusDoPedido.RECEBIDO)).thenReturn(pedidos);

        List<PedidoPresenter> resultado = pedidoController.obterPedidosPorStatus(StatusDoPedido.RECEBIDO);

        verify(obterPedidosPorStatusUseCase, times(1)).execute(StatusDoPedido.RECEBIDO);

        assertEquals(2, resultado.size());
    }

    @Test
    void aoAtualizarStatusPedido_DeveRetornarPedidoAtualizado() {

        UUID pedidoId = UUID.randomUUID();
        Pedido pedidoAtualizado = PedidoBuilder.build();
        pedidoAtualizado.setStatusDoPedido(StatusDoPedido.EM_PREPARACAO);

        when(atualizarStatusPedidoUseCase.execute(pedidoId, StatusDoPedido.EM_PREPARACAO)).thenReturn(pedidoAtualizado);

        PedidoPresenter resultado = pedidoController.atualizarStatusPedido(pedidoId, StatusDoPedido.EM_PREPARACAO);

        verify(atualizarStatusPedidoUseCase, times(1)).execute(pedidoId, StatusDoPedido.EM_PREPARACAO);
    }
}
