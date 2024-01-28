package br.com.fiap.soat.tech_challenge.fase4msproducao.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.soat.tech_challenge.fase4msproducao.controllers.PedidoController;
import br.com.fiap.soat.tech_challenge.fase4msproducao.presenters.PedidoPresenter;

@ExtendWith(MockitoExtension.class)
class PedidoApiTest {

    @Mock
    private PedidoController pedidoController;

    @InjectMocks
    private PedidoApi pedidoApi;

    @Test
    void obterPedidoTest() {
        when(pedidoController.obterTodosPedidos()).thenReturn(new ArrayList<>());
        ResponseEntity<List<PedidoPresenter>> response = pedidoApi.obterPedidos(null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void atualizarStatusPedidoTest() {
        when(pedidoController.atualizarStatusPedido(null, null)).thenReturn(null);
        ResponseEntity<PedidoPresenter> response = pedidoApi.atualizarStatusPedido(null, null);
        assert response.getStatusCode().value() == 200;
    }
}
