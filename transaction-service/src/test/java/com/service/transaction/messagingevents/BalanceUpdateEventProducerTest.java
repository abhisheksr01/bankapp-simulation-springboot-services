package com.service.transaction.messagingevents;

import com.service.transaction.model.BalanceUpdateVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static com.service.transaction.helper.TestData.getBalanceUpdateEventMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BalanceUpdateEventProducerTest {
    public static final String MOCK_TOPIC_NAME = "mock-topicName";
    private BalanceUpdateEventProducer balanceUpdateEventProducer;
    private KafkaTemplate<String, BalanceUpdateVO> mockKafkaTemplate;

    @BeforeEach
    void setUp() {
        mockKafkaTemplate = mock(KafkaTemplate.class);
        balanceUpdateEventProducer = new BalanceUpdateEventProducer(mockKafkaTemplate, MOCK_TOPIC_NAME);
    }

    @Test
    void produceEventMessage_shouldCallTheKafkaTemplate_whenTransactionVOIsPassed() {
        CompletableFuture<SendResult<String, BalanceUpdateVO>> future = new CompletableFuture<>();
        when(mockKafkaTemplate.send(eq(MOCK_TOPIC_NAME), any(BalanceUpdateVO.class)))
                .thenReturn(future);

        balanceUpdateEventProducer.publishUpdateBalance(getBalanceUpdateEventMessage());

        verify(mockKafkaTemplate, times(1))
                .send(MOCK_TOPIC_NAME, getBalanceUpdateEventMessage());
    }
}
