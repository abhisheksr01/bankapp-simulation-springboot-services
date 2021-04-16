package com.service.transaction.messagingevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import static com.service.transaction.helper.TestData.getBalanceUpdateEventMessage;
import static org.mockito.Mockito.*;

class BalanceUpdateEventProducerTest {
    public static final String MOCK_TOPIC_NAME = "mock-topicName";
    private BalanceUpdateEventProducer balanceUpdateEventProducer;
    private KafkaTemplate mockKafkaTemplate = mock(KafkaTemplate.class);

    @BeforeEach
    void setUp() {
        balanceUpdateEventProducer = new BalanceUpdateEventProducer(mockKafkaTemplate, MOCK_TOPIC_NAME);
    }

    @Test
    void produceEventMessage_shouldCallTheKafkaTemplate_whenTransactionVOIsPassed() {
        when(mockKafkaTemplate.send(MOCK_TOPIC_NAME, getBalanceUpdateEventMessage())).
                thenReturn(mock(ListenableFuture.class));

        balanceUpdateEventProducer.publishUpdateBalance(getBalanceUpdateEventMessage());

        verify(mockKafkaTemplate, times(1))
                .send(MOCK_TOPIC_NAME, getBalanceUpdateEventMessage());
    }
}