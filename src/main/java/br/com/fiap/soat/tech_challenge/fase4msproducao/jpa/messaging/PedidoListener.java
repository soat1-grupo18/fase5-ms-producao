package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.messaging;
import br.com.fiap.soat.tech_challenge.fase4msproducao.api.requests.PedidoEmProducaoRequest;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.ItemDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPagamento;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.StatusDoPedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.gateways.PedidoGatewayPort;
import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public abstract class PedidoListener implements PedidoGatewayPort {
    private static final Logger logger = LoggerFactory.getLogger(PedidoListener.class);

    public static final String EVENT_TYPE_CUSTOM_HEADER = "eventType";

    private final PedidoRepository pedidoRepository;


    private final ObjectMapper objectMapper;

    public PedidoListener(PedidoRepository pedidoRepository, ObjectMapper objectMapper){
        this.pedidoRepository = pedidoRepository;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${events.queues.queue1_url}")
    public void obterPedido (String message) {
        try {
            logger.info("===============PEDIDO RECEBIDO COM SUCESSO===============> {}", message);

            PedidoEmProducaoRequest pedidoEmProducaoRequest = objectMapper.readValue(message, PedidoEmProducaoRequest.class);
            UUID pedidoId = pedidoEmProducaoRequest.getPedidoId();
            UUID clienteId = pedidoEmProducaoRequest.getClienteId();
            BigDecimal preco = pedidoEmProducaoRequest.getPreco();
            StatusDoPedido statusDoPedido = pedidoEmProducaoRequest.toDomain().getStatusDoPedido();
            StatusDoPagamento statusDoPagamento = pedidoEmProducaoRequest.toDomain().getStatusDoPagamento();
            List<ItemDoPedido> itens = pedidoEmProducaoRequest.toDomain().getItens();
            UUID pagamentoId = pedidoEmProducaoRequest.toDomain().getPagamentoId();
            LocalDateTime dataDeCriacao = pedidoEmProducaoRequest.toDomain().getDataDeCriacao();

            Pedido pedido = new Pedido(pedidoId, null, clienteId, preco, statusDoPedido, statusDoPagamento, itens, pagamentoId, dataDeCriacao);

            //pedidoRepository.save(pedido);

            logger.info("Pedido salvo com sucesso: {}", pedido);
        }catch (Exception e){
            logger.error("Erro ao processar o pedido recebido: {}", message, e);
        }
    }

}