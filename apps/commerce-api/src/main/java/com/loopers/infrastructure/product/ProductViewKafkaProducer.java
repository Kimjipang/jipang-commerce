package com.loopers.infrastructure.product;

import com.loopers.domain.product.ProductViewedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductViewKafkaProducer {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendProductViewedMessage(ProductViewedMessage message) {
        kafkaTemplate.send("product-viewed-topic", message);
    }
}
