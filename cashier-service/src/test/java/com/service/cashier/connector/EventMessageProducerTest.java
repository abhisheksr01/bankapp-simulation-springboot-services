package com.service.cashier.connector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.mockito.Mockito.*;

class EventMessageProducerTest {

    public static final String MOCK_TOPIC_NAME = "mock-topicName";
    private EventMessageProducer eventMessageProducer;
    private KafkaTemplate mockKafkaTemplate = mock(KafkaTemplate.class);

    @BeforeEach
    void setUp() {
        eventMessageProducer = new EventMessageProducer(mockKafkaTemplate, MOCK_TOPIC_NAME);
    }

    @Test
    void produceEventMessage_shouldCallTheKafkaTemplate_whenTransactionVOIsPassed() {
        when(mockKafkaTemplate.send(MOCK_TOPIC_NAME, getCreditTransaction())).thenReturn(mock(ListenableFuture.class));

        eventMessageProducer.produceEventMessage(getCreditTransaction());

        verify(mockKafkaTemplate, times(1))
                .send(MOCK_TOPIC_NAME, getCreditTransaction());
    }
}