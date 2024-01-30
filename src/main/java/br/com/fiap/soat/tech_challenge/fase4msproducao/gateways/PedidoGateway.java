package br.com.fiap.soat.tech_challenge.fase4msproducao.gateways;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.exceptions.PedidoNaoEncontradoException;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.entities.PedidoJpaEntity;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Component
public class PedidoGateway implements PedidoGatewayPort {
    public PedidoGateway(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public Pedido inserirPedido(Pedido pedido) {
        PedidoJpaEntity pedidoJpaEntity = PedidoJpaEntity.fromDomain(pedido);

        pedidoRepository.save(pedidoJpaEntity);

        return pedidoJpaEntity.toDomain();
    }

    @Override
    public Pedido obterPedido(UUID pedidoOriginalId) {
        var pedidoO = pedidoRepository.findByPedidoOriginalId(pedidoOriginalId);
        if (pedidoO.isEmpty()) {
            throw PedidoNaoEncontradoException.aPartirDoId(pedidoOriginalId);
        }
        return pedidoO.get(0).toDomain();
    }

    @Override
    public List<Pedido> obterTodosPedidos() {
        return StreamSupport.stream(pedidoRepository.findAll().spliterator(), false)
                .map(PedidoJpaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public void atualizarPedido(Pedido pedido) {
        PedidoJpaEntity pedidoJpaEntity = PedidoJpaEntity.fromDomain(pedido);
        pedidoRepository.save(pedidoJpaEntity);
    }

    @Override
    public List<Pedido> obterPedidosPorStatus(StatusDoPedido... statuses) {
        return pedidoRepository.obterPedidosPorStatus(statuses).stream()
                .map(PedidoJpaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean consultarStatusPagamento(UUID pedidoId) {
        throw new UnsupportedOperationException("Unimplemented method 'consultarStatusPagamento'");
    }

    @Override
    public Optional<Pedido> obterPedidoComPagamentoId(UUID pagamentoId) {
        throw new UnsupportedOperationException("Unimplemented method 'obterPedidoComPagamentoId'");
    }
}
