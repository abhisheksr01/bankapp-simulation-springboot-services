package com.service.cashier.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.cashier.model.TransactionVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.kafka.test.assertj.KafkaConditions.key;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasValue;


@RunWith(SpringRunner.class)
@DirtiesContext
@SpringBootTest
@ActiveProfiles("test")
public class EventMessageProducerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventMessageProducerIT.class);
    private static String TOPIC_NAME = "transaction";
    @ClassRule
    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, TOPIC_NAME);
    @Autowired
    private EventMessageProducer eventMessageProducer;
    private KafkaMessageListenerContainer<String, TransactionVO> container;
    private BlockingQueue<ConsumerRecord<String, String>> consumerRecords;

    @Before
    public void setUp() {
        consumerRecords = new LinkedBlockingQueue<>();

        ContainerProperties containerProperties = new ContainerProperties(TOPIC_NAME);

        Map<String, Object> consumerProperties = KafkaTestUtils.consumerProps(
                "sender", "false", embeddedKafka.getEmbeddedKafka());

        DefaultKafkaConsumerFactory<String, TransactionVO> consumer = new DefaultKafkaConsumerFactory<>(consumerProperties);

        container = new KafkaMessageListenerContainer<>(consumer, containerProperties);
        container.setupMessageListener((MessageListener<String, String>) record -> {
            System.out.println("Listened message='{}'" + record.toString());
            consumerRecords.add(record);
        });
        container.start();

        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getEmbeddedKafka().getPartitionsPerTopic());
    }

    @After
    public void tearDown() {
        container.stop();
    }

    @Test
    public void produceEventMessage_shouldReceiveAnEvent_whenAnMessagingEventIsSent() throws InterruptedException, IOException {
        eventMessageProducer.produceEventMessage(getCreditTransaction());

        ConsumerRecord<String, String> received = consumerRecords.poll(10, TimeUnit.SECONDS);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(getCreditTransaction());

        assertThat(received, hasValue(json));
        assertThat(received).has(key(null));
    }

}