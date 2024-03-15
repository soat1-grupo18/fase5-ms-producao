package br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.messaging;

import br.com.fiap.soat.tech_challenge.fase4msproducao.jpa.repositories.PedidoRepository;

import static org.awaitility.Awaitility.await;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.UUID;

@ActiveProfiles("development")
@SpringBootTest
public class SpringCloudAwsSQSLiveTest extends BaseSqsIntegrationTests {

    private static final Logger logger = LoggerFactory.getLogger(SpringCloudAwsSQSLiveTest.class);

    @Autowired
    private SqsTemplate sqsTemplate;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EventQueuesProperties eventQueuesProperties;

    @Test
    void givenAIdPayload_whenSend_shouldReceive() {

        UUID pedidoId = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

        sqsTemplate.send(to -> to.queue(eventQueuesProperties.getQueue1_name())
                .payload(pedidoId));
        logger.info("Message sent with payload {}", pedidoId);


        await().atMost(Duration.ofSeconds(3))
                .until(() -> !pedidoRepository.findByPedidoOriginalId(pedidoId)
                        .isEmpty());
    }
}