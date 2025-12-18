package com.loopers.infrastructure.outbox;

import com.loopers.domain.outbox.OutboxEvent;
import com.loopers.domain.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {
    private final OutboxJpaRepository outBoxJpaRepository;

    @Override
    public OutboxEvent save(OutboxEvent outBoxEvent) {
        return outBoxJpaRepository.save(outBoxEvent);
    }

    @Override
    public List<OutboxEvent> findPending(int limit) {
        return outBoxJpaRepository.findPending(limit);
    }

}
