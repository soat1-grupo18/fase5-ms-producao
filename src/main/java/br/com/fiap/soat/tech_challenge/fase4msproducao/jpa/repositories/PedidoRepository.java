package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.entities.PedidoJpaEntity;

public interface PedidoRepository extends CrudRepository<PedidoJpaEntity, UUID> {
    List<PedidoJpaEntity> findByPagamentoId(UUID pagamentoId);

    List<PedidoJpaEntity> findByPedidoOriginalId(UUID pedidoOriginalId);

    @Query("select p from PedidoJpaEntity p " +
            "where p.statusDoPedido IN :statuses " +
            "order by p.dataDeCriacao asc, p.statusDoPedido desc")
    List<PedidoJpaEntity> obterPedidosPorStatus(StatusDoPedido... statuses);
}
