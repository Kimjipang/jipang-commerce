package com.loopers.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_metrics")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductMetric extends BaseEntity {
    @Column(name = "ref_product_id", nullable = false)
    private Long productId;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    @Column(name = "like_count", nullable = false)
    private Long likeCount;

    @Column(name = "sales_volume", nullable = false)
    private Long salesVolume;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private ProductEventType eventType;

    public ProductMetric(Long productId, Long viewCount, Long likeCount, Long salesVolume, ProductEventType eventType) {
        this.productId = productId;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.salesVolume = salesVolume;
        this.eventType = eventType;
    }

    public static ProductMetric of(Long productId, Long viewCount, Long likeCount, Long salesVolume, ProductEventType eventType) {
        return new ProductMetric(productId, viewCount, likeCount, salesVolume, eventType);
    }

    public void increaseProductMetric(ProductEventType eventType) {
        switch (eventType) {
            case PRODUCT_VIEWED -> increaseViewCount();
            case PRODUCT_LIKED -> increaseLikeCount();
            case PRODUCT_SALES -> increaseSalesVolume();
        }
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void increaseSalesVolume() {
        this.salesVolume++;
    }

}
