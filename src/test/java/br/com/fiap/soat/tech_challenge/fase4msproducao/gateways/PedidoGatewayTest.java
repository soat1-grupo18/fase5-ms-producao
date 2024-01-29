package br.com.fiap.soat.tech_challenge.fase4msproducao.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.soat.tech_challenge.fase4msproducao.builders.PedidoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions.PedidoNaoEncontradoException;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.entities.PedidoJpaEntity;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoGatewayTest {
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoGateway pedidoGateway;

    @Test
    void aoInserirPedido_DeveRetornarPedidoInserido() {

        Pedido pedido = PedidoBuilder.build();
        pedido.setItens(new ArrayList<>());
        PedidoRepository pedidoRepositoryMock = mock(PedidoRepository.class);
        when(pedidoRepositoryMock.save(any(PedidoJpaEntity.class)))
                .thenReturn(PedidoJpaEntity.fromDomain(pedido));

        PedidoGatewayPort pedidoGateway = new PedidoGateway(pedidoRepositoryMock);
        Pedido resultado = pedidoGateway.inserirPedido(pedido);
        assertNotNull(resultado);
        assertEquals(pedido.getId(), resultado.getId());
        assertEquals(pedido.getStatusDoPedido(), resultado.getStatusDoPedido());
    }

    @Test
    void aoObterPedido_DeveRetornarPedidoExistente() {

        UUID pedidoId = UUID.randomUUID();
        UUID pedidoOriginalId = UUID.randomUUID();
        Pedido pedido = PedidoBuilder.build();
        pedido.setId(pedidoId);
        pedido.setPedidoOriginalId(pedidoOriginalId);

        PedidoJpaEntity pedidoJpaEntity = Mockito.mock(PedidoJpaEntity.class);

        when(pedidoJpaEntity.toDomain()).thenReturn(pedido);

        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);

        when(pedidoRepository.findByPedidoOriginalId(Mockito.eq(pedidoOriginalId))).thenReturn(List.of(pedidoJpaEntity));

        PedidoGatewayPort pedidoGateway = new PedidoGateway(pedidoRepository);
        Pedido resultado = pedidoGateway.obterPedido(pedidoOriginalId);

        verify(pedidoRepository, times(1)).findByPedidoOriginalId(Mockito.eq(pedidoOriginalId));
        assertNotNull(resultado);
        assertEquals(pedido, resultado);

    }

    @Test
    void aoObterPedido_ComPedidoInexistente_DeveLancarExcecao() {

        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);

        when(pedidoRepository.findByPedidoOriginalId(Mockito.any(UUID.class))).thenReturn(List.of());

        PedidoGatewayPort pedidoGateway = new PedidoGateway(pedidoRepository);

        assertThrows(PedidoNaoEncontradoException.class, () -> {
            pedidoGateway.obterPedido(UUID.randomUUID());
        });

        verify(pedidoRepository, times(1)).findByPedidoOriginalId(Mockito.any(UUID.class));
    }

    @Test
    void aoObterTodosPedidos_DeveRetornarListaCompletaDePedidos() {

        UUID pedidoId = UUID.randomUUID();
        UUID pedidoOriginalId = UUID.randomUUID();
        Pedido pedido = PedidoBuilder.build();
        pedido.setId(pedidoId);
        pedido.setPedidoOriginalId(pedidoOriginalId);

        PedidoJpaEntity pedidoJpaEntity = Mockito.mock(PedidoJpaEntity.class);

        when(pedidoJpaEntity.toDomain()).thenReturn(pedido);

        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);

        when(pedidoRepository.findByPedidoOriginalId(Mockito.eq(pedidoOriginalId))).thenReturn(List.of(pedidoJpaEntity));

        PedidoGatewayPort pedidoGateway = new PedidoGateway(pedidoRepository);

        Pedido resultado = pedidoGateway.obterPedido(pedidoOriginalId);

        verify(pedidoRepository, times(1)).findByPedidoOriginalId(Mockito.eq(pedidoOriginalId));

        assertNotNull(resultado);
        assertEquals(pedido, resultado);
    }

    @Test
    void aoAtualizarPedido_DeveAtualizarNoRepositorio() {
        Pedido pedido = Mockito.mock(Pedido.class);
        when(pedido.getItens()).thenReturn(new ArrayList<>());

        PedidoJpaEntity pedidoJpaEntity = PedidoJpaEntity.fromDomain(pedido);

        pedidoGateway.atualizarPedido(pedido);

        assertEquals(pedidoJpaEntity, pedidoJpaEntity);
    }

    @Test
    void aoObterPedidosPorStatus_DeveFiltarListaPorStatus() {

        PedidoRepository pedidoRepository = Mockito.mock(PedidoRepository.class);
        PedidoJpaEntity pedidoJpaEntity1 = Mockito.mock(PedidoJpaEntity.class);
        PedidoJpaEntity pedidoJpaEntity2 = Mockito.mock(PedidoJpaEntity.class);

        StatusDoPedido[] statuses = { StatusDoPedido.RECEBIDO, StatusDoPedido.EM_PREPARACAO };
        when(pedidoRepository.obterPedidosPorStatus(statuses))
                .thenReturn(Arrays.asList(pedidoJpaEntity1, pedidoJpaEntity2));

        Pedido pedido1 = PedidoBuilder.build();
        Pedido pedido2 = PedidoBuilder.build();
        when(pedidoJpaEntity1.toDomain()).thenReturn(pedido1);
        when(pedidoJpaEntity2.toDomain()).thenReturn(pedido2);

        PedidoGateway pedidoGateway = new PedidoGateway(pedidoRepository);

        List<Pedido> pedidos = pedidoGateway.obterPedidosPorStatus(StatusDoPedido.RECEBIDO,
                StatusDoPedido.EM_PREPARACAO);

        assertEquals(2, pedidos.size());
        assertEquals(pedido1, pedidos.get(0));
        assertEquals(pedido2, pedidos.get(1));

        verify(pedidoRepository, times(1)).obterPedidosPorStatus(statuses);

    }
}