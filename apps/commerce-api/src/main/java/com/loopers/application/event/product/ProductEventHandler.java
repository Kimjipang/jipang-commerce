package com.loopers.application.event.product;

import com.loopers.application.product.ProductLookedUpEvent;
import com.loopers.domain.actionlog.UserActionLog;
import com.loopers.infrastructure.actionlog.UserActionLogJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProductEventHandler {
    private final UserActionLogJpaRepository userActionLogJpaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onProductLookedUp(ProductLookedUpEvent event) {
        UserActionLog userActionLog = UserActionLog.create(event);
        userActionLogJpaRepository.save(userActionLog);

    }
}
