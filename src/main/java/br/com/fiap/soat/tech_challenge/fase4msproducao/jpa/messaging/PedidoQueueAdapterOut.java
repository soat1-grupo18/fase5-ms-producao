package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.messaging;

import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.IPedidoQueueAdapterOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class PedidoQueueAdapterOut implements IPedidoQueueAdapterOUT {
    private static final Logger logger = LoggerFactory.getLogger(PedidoQueueAdapterOut.class);

    @Autowired
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final String pedidosRecebidos;

    @Autowired
    public PedidoQueueAdapterOut(QueueMessagingTemplate queueMessagingTemplate, @Value("${events.queues.queue1_name}") String pedidosRecebidos){
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.pedidosRecebidos = pedidosRecebidos;
    }

    @Override
    public void publish(String message){
        try{
            this.queueMessagingTemplate.convertAndSend(pedidosRecebidos, message);
            logger.info("Publicação na fila realizada com sucesso! Mensagem: {}", message);
        } catch (Exception e) {
            logger.error("Erro ao publicar na fila. Mensagem: {}", message, e);
            throw new RuntimeException("Erro ao publicar na fila", e);
        }
    }

}
