package com.loopers.infrastructure.outbox;

import com.loopers.domain.outbox.OutboxEvent;
import com.loopers.domain.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaOutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Scheduled(fixedDelayString = "1000")
    @Transactional
    public void publish() {
        List<OutboxEvent> events = outboxRepository.findPending(5);

        for (OutboxEvent event : events) {
            kafkaTemplate.send("product-viewed", String.valueOf(event.getAggregateId()), event);

            event.markAsProcessed();
        }

    }
}
