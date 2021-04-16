package com.service.balance.messagingevents;

import com.service.balance.service.BalanceService;
import org.junit.jupiter.api.Test;

import static com.service.balance.helper.TestData.getBalanceUpdateVO;
import static org.mockito.Mockito.*;

class BalanceEventConsumerTest {

    @Test
    void consumeBalanceUpdateEvent_shouldCallService_whenBalanceUpdateEventIsReceived() {
        BalanceService mockBalanceService = mock(BalanceService.class);

        BalanceEventConsumer balanceEventConsumer = new BalanceEventConsumer(mockBalanceService);

        balanceEventConsumer.consumeBalanceUpdateEvent(getBalanceUpdateVO());

        verify(mockBalanceService, times(1)).updateBalance(getBalanceUpdateVO());
    }
}