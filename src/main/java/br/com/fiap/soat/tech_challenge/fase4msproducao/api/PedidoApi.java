package br.com.fiap.soat.tech_challenge.fase4msproducao.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import br.com.fiap.soat.tech_challenge.fase4msproducao.controllers.PedidoController;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.presenters.PedidoPresenter;

@RestController
public class PedidoApi {

    private final PedidoController pedidoController;

    public PedidoApi(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @Operation(summary = "Obter pedidos", description = "Retorna uma lista de pedidos, opcionalmente filtrada por status.")
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoPresenter>> obterPedidos(
            @RequestParam(name = "status", required = false) StatusDoPedido[] statuses) {
        List<PedidoPresenter> pedidos;

        if (statuses != null && statuses.length > 0) {
            pedidos = pedidoController.obterPedidosPorStatus(statuses);
        } else {
            pedidos = pedidoController.obterTodosPedidos();
        }

        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Atualizar status do pedido", description = "Altera o status de um pedido identificado pelo seu id.")
    @PutMapping("/pedidos/{pedidoId}/{statusDoPedido}")
    public ResponseEntity<PedidoPresenter> atualizarStatusPedido(@PathVariable UUID pedidoId,
            @PathVariable StatusDoPedido statusDoPedido) {
        return ResponseEntity.ok(pedidoController.atualizarStatusPedido(pedidoId, statusDoPedido));
    }
}
