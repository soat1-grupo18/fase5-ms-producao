package br.com.fiap.soat.tech_challenge.fase4msproducao.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.AtualizarStatusPedidoUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterPedidosPorStatusUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.ObterTodosPedidosUseCasePort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.presenters.PedidoPresenter;

@RestController
public class PedidoController {
    private final ObterTodosPedidosUseCasePort obterTodosPedidosUseCase;
    private final ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase;
    private final AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase;

    public PedidoController(ObterTodosPedidosUseCasePort obterTodosPedidosUseCase,
            ObterPedidosPorStatusUseCasePort obterPedidosPorStatusUseCase,
            AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase) {
        this.obterTodosPedidosUseCase = obterTodosPedidosUseCase;
        this.obterPedidosPorStatusUseCase = obterPedidosPorStatusUseCase;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    public List<PedidoPresenter> obterTodosPedidos() {
        List<Pedido> pedidos = obterTodosPedidosUseCase.execute();
        return pedidos.stream().map(PedidoPresenter::fromDomain).collect(Collectors.toList());
    }

    public List<PedidoPresenter> obterPedidosPorStatus(StatusDoPedido... statuses) {
        List<Pedido> pedidos = obterPedidosPorStatusUseCase.execute(statuses);
        return pedidos.stream().map(PedidoPresenter::fromDomain).collect(Collectors.toList());
    }

    public PedidoPresenter atualizarStatusPedido(UUID pedidoId, StatusDoPedido statusDoPedido) {
        Pedido pedido = atualizarStatusPedidoUseCase.execute(pedidoId, statusDoPedido);
        return PedidoPresenter.fromDomain(pedido);
    }
}
