package com.hiberus.services.Impl;

import com.hiberus.clients.seatBookingServiceClient;
import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;
import com.hiberus.services.SeatBookingServiceFeign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("feign-SeatBookingService")
@AllArgsConstructor
public class SeatBookingServiceFeignImpl implements SeatBookingServiceFeign {

    private final seatBookingServiceClient seatBookingServiceClient;

    @Override
    public ValidationResponseDto validateSeats(ValidationRequestDto request) {
        return seatBookingServiceClient.validateSeats(request);
    }
}