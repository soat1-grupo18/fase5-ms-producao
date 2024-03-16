package br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs;

import br.com.fiap.soat.tech_challenge.fase4msproducao.api.sqs.requests.PedidoRecebidoRequest;
import br.com.fiap.soat.tech_challenge.fase4msproducao.entities.Pedido;
import br.com.fiap.soat.tech_challenge.fase4msproducao.interfaces.usecases.CriarPedidoEmProducaoUseCasePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PedidoRecebidoListener {
    private static final Logger logger = LoggerFactory.getLogger(PedidoRecebidoListener.class);
    private final CriarPedidoEmProducaoUseCasePort criarPedidoEmProducaoUseCasePort;

    public PedidoRecebidoListener(CriarPedidoEmProducaoUseCasePort criarPedidoEmProducaoUseCasePort) {
        this.criarPedidoEmProducaoUseCasePort = criarPedidoEmProducaoUseCasePort;
    }

    @SqsListener("${sqs.queues.pedido-recebido}")
    public void obterPedido (PedidoRecebidoRequest pedidoRecebidoRequest) {
        try {
            logger.info("===============PEDIDO RECEBIDO COM SUCESSO===============> {}", pedidoRecebidoRequest);

            Pedido pedido = pedidoRecebidoRequest.toDomain();

            criarPedidoEmProducaoUseCasePort.execute(pedido);

            logger.info("Pedido salvo com sucesso: {}", pedido);
        }catch (Exception e){
            logger.error("Erro ao processar o pedido recebido: {}", pedidoRecebidoRequest, e);
        }
    }
}