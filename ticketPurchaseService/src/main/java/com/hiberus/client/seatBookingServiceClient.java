package com.hiberus.client;

import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "seatBookingService")
public interface seatBookingServiceClient {

    @PostMapping("/v1/seats/validate")
    ValidationResponseDto validateSeats(@RequestBody ValidationRequestDto request);
}