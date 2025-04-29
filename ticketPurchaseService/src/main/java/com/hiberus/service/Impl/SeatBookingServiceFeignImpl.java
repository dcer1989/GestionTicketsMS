package com.hiberus.service.Impl;

import com.hiberus.client.seatBookingServiceClient;
import com.hiberus.dto.ValidationRequestDto;
import com.hiberus.dto.ValidationResponseDto;
import com.hiberus.service.SeatBookingServiceFeign;
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