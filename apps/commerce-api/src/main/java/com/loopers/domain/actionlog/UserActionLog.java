package com.loopers.domain.actionlog;

import com.loopers.application.product.ProductLookedUpEvent;
import com.loopers.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_action_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserActionLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    유저는 추후 추가 예정
     */

    @Column(name = "product_id", nullable = false)
    private Long productId;

    public UserActionLog(Long productId) {
        this.productId = productId;
    }

    public static UserActionLog create(ProductLookedUpEvent event) {
        return new UserActionLog(event.productId());
    }
}
