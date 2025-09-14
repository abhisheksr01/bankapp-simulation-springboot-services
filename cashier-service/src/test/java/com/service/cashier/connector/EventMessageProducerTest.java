package com.service.cashier.connector;

import com.service.cashier.model.TransactionVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.mockito.Mockito.*;

class EventMessageProducerTest {

    public static final String MOCK_TOPIC_NAME = "mock-topicName";
    private EventMessageProducer eventMessageProducer;
    private KafkaTemplate<String, TransactionVO> mockKafkaTemplate = mock(KafkaTemplate.class);

    @BeforeEach
    void setUp() {
        eventMessageProducer = new EventMessageProducer(mockKafkaTemplate, MOCK_TOPIC_NAME);
    }

    @Test
    void produceEventMessage_shouldCallTheKafkaTemplate_whenTransactionVOIsPassed() {
        CompletableFuture<SendResult<String, TransactionVO>> future = new CompletableFuture<>();
        when(mockKafkaTemplate.send(MOCK_TOPIC_NAME, getCreditTransaction())).thenReturn(future);

        eventMessageProducer.produceEventMessage(getCreditTransaction());

        verify(mockKafkaTemplate, times(1))
                .send(MOCK_TOPIC_NAME, getCreditTransaction());
    }
}
