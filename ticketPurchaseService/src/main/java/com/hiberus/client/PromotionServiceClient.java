package com.hiberus.client;

import com.hiberus.dto.ApplyPromotionRequest;
import com.hiberus.dto.ApplyPromotionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "promotionService")
public interface PromotionServiceClient {

    @PostMapping("/v1/promotions/apply")
    @ResponseStatus(HttpStatus.OK)
    ApplyPromotionResponse applyPromotion(@RequestBody ApplyPromotionRequest request);
}