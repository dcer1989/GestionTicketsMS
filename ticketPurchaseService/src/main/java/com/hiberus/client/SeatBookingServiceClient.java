package com.hiberus.client;

import com.hiberus.dto.UpdateRequest;
import com.hiberus.dto.UpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "seatBookingService")
public interface SeatBookingServiceClient {

    @PostMapping("/v1/reservations/update")
    @ResponseStatus(HttpStatus.OK)
    UpdateResponse updateReservations(@RequestBody UpdateRequest updateRequest);
}