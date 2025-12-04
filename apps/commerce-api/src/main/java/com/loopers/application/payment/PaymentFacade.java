package com.loopers.application.payment;

import com.loopers.infrastructure.payment.client.PgClient;
import com.loopers.interfaces.api.ApiResponse;
import com.loopers.interfaces.api.payment.PaymentV1Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentFacade {
    private final PgClient pgClient;

    public PaymentV1Dto.TransactionResponse pay(String userId, PaymentV1Dto.PaymentRequest request) {
        ApiResponse<PaymentV1Dto.TransactionResponse> result = pgClient.requestPayment(userId, request);

        PaymentV1Dto.TransactionResponse response = result.data();

        return response;
    }
}
