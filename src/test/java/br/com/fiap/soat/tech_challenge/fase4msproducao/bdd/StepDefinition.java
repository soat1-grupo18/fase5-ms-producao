package br.com.fiap.soat.tech_challenge.fase4msproducao.bdd;

import static org.mockito.Mockito.when;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.soat.tech_challenge.fase4msproducao.controllers.PedidoController;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.presenters.PedidoPresenter;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class StepDefinition {

    @Autowired
    private PedidoController pedidoController;

    private List<PedidoPresenter> statusAtual;

    @Dado("que o pedido entrou na fila")
    public void que_o_pedido_entrou_na_fila() {
        statusAtual = pedidoController.obterTodosPedidos();
    }

    @Quando("o status inicial e recebido")
    public void o_status_inicial_e_recebido() {
        statusAtual = pedidoController.obterPedidosPorStatus(StatusDoPedido.RECEBIDO);
    }

    @Então("direciono o pedido para o status de andamento")
    public void direciono_o_pedido_para_o_status_de_andamento() {
        when(statusAtual).thenReturn(pedidoController.obterPedidosPorStatus(StatusDoPedido.EM_PREPARACAO));
    }
}
