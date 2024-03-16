package br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs;

import br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs.requests.PagamentoEvent;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.AtualizarStatusPedidoUseCasePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class PagamentoListener {
    private final Logger logger = LoggerFactory.getLogger(PagamentoListener.class);
    private final ObjectMapper mapper;
    private final AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase;

    public PagamentoListener(ObjectMapper mapper, AtualizarStatusPedidoUseCasePort atualizarStatusPedidoUseCase) {
        this.mapper = mapper;
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @SqsListener("${sqs.queues.pagamento-aprovado}")
    public void receberEventoDePagamentoAprovado(String evento) throws JsonProcessingException {
        logger.info("Processando evento de pagamento aprovado: {}", evento);
        PagamentoEvent pagamentoAprovado = mapper.readValue(evento, PagamentoEvent.class);
        atualizarStatus(pagamentoAprovado);
    }

    @SqsListener("${sqs.queues.pagamento-recusado}")
    public void receberEventoDePagamentoRecusado(String evento) throws JsonProcessingException {
        logger.info("Processando evento de pagamento recusado: {}", evento);
        PagamentoEvent pagamentoRecusado = mapper.readValue(evento, PagamentoEvent.class);
        atualizarStatus(pagamentoRecusado);
    }

    @Transactional
    private void atualizarStatus(PagamentoEvent pagamento) {
        StatusDoPagamento statusDoPagamento = StatusDoPagamento.valueOf(pagamento.getStatus());
        if (statusDoPagamento.equals(StatusDoPagamento.APROVADO)) {
            logger.info("Pagamento aprovado para o pedido {}", pagamento.getPedidoId());
            atualizarStatusPedidoUseCase.execute(UUID.fromString(pagamento.getPedidoId()), StatusDoPedido.EM_PREPARACAO, statusDoPagamento);
        } else {
            logger.info("Pagamento recusado para o pedido {}", pagamento.getPedidoId());
            atualizarStatusPedidoUseCase.execute(UUID.fromString(pagamento.getPedidoId()), StatusDoPedido.RECUSADO, statusDoPagamento);
        }
    }
}
