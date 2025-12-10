package com.loopers.application.event;

import com.loopers.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class LikeEventHandler {
    private final ProductRepository productRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onLikeCreated(LikeCreateEvent event) {

    }
}
