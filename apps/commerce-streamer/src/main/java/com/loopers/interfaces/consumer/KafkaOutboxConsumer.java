package com.loopers.interfaces.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopers.confg.kafka.KafkaConfig;
import com.loopers.domain.OutboxEvent;
import com.loopers.domain.ProductEventType;
import com.loopers.domain.ProductMetric;
import com.loopers.domain.ProductMetricRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaOutboxConsumer {
    private final ProductMetricRepository productMetricRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = {"product-viewed"},
            containerFactory = KafkaConfig.BATCH_LISTENER
    )
    public void demoListener(
            List<ConsumerRecord<String, String>> messages,
            Acknowledgment acknowledgment
    ) throws JsonProcessingException {
        for (var record : messages) {
            OutboxEvent value = objectMapper.readValue(record.value(), OutboxEvent.class);
            Long productId = value.aggregateId();
            String eventType = value.eventType();

            ProductMetric productMetric = productMetricRepository.findByProductId(productId);

            if (productMetric == null) {
                productMetricRepository.save(
                        ProductMetric.of(
                                productId,
                                0L,
                                0L,
                                0L,
                                ProductEventType.valueOf(eventType)
                        )
                );
            }
            else {
                productMetric.increaseProductMetric(ProductEventType.valueOf(eventType));
            }

        }
        acknowledgment.acknowledge();
    }
}
