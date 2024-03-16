package br.com.fiap.soat.tech_challenge.fase4msproducao.usescases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions.StatusPedidoNaoAtualizadoException;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.usecases.AtualizarStatusPedidoUseCase;

@ExtendWith(MockitoExtension.class)
public class AtualizarStatusPedidoUseCaseTest {

    @Mock
    private PedidoGatewayPort pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @Test
    void execute_DeveAtualizarStatusPedido() {

        UUID pedidoOriginalId = UUID.randomUUID();
        StatusDoPedido statusAtual = StatusDoPedido.RECEBIDO;
        StatusDoPedido novoStatus = StatusDoPedido.EM_PREPARACAO;

        Pedido pedidoMock = Mockito.mock(Pedido.class);
        when(pedidoMock.getStatusDoPedido()).thenReturn(statusAtual);

        PedidoGatewayPort pedidoGatewayMock = Mockito.mock(PedidoGatewayPort.class);
        when(pedidoGatewayMock.obterPedido(pedidoOriginalId)).thenReturn(pedidoMock);

        AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase = new AtualizarStatusPedidoUseCase(pedidoGatewayMock);
        Pedido pedidoAtualizado = atualizarStatusPedidoUseCase.execute(pedidoOriginalId, novoStatus, StatusDoPagamento.APROVADO);

        assertNotNull(novoStatus);
        verify(pedidoGatewayMock).atualizarPedido(pedidoAtualizado);
    }

    @Test
    void execute_ComProximoStatusInvalido_DeveLancarExcecao() {

        UUID pedidoOriginalId = UUID.randomUUID();
        StatusDoPedido statusAtual = StatusDoPedido.RECEBIDO;
        StatusDoPedido novoStatus = StatusDoPedido.FINALIZADO;

        Pedido pedidoMock = Mockito.mock(Pedido.class);
        when(pedidoMock.getStatusDoPedido()).thenReturn(statusAtual);

        PedidoGatewayPort pedidoGatewayMock = Mockito.mock(PedidoGatewayPort.class);
        when(pedidoGatewayMock.obterPedido(pedidoOriginalId)).thenReturn(pedidoMock);

        AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase = new AtualizarStatusPedidoUseCase(pedidoGatewayMock);
        assertThrows(StatusPedidoNaoAtualizadoException.class, () -> {
            atualizarStatusPedidoUseCase.execute(pedidoOriginalId, novoStatus, StatusDoPagamento.APROVADO);
        });
    }
}
