package com.hiberus.usecase;

import com.hiberus.exception.ShowtimeNotFoundException;
import com.hiberus.model.Showtime;
import com.hiberus.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetShowtimeByShowShowtimeIdsUseCase {

    private final ShowtimeRepository showtimeRepository;

    public Showtime getShowtimeByShowShowtimeIds(UUID showId, UUID showtimeId) {
        log.info("Fetching showtime with ID: {} for show ID: {}", showtimeId, showId);

        return showtimeRepository.findByShowId(showId).stream()
                .filter(showtime -> showtime.getId().equals(showtimeId))
                .findFirst()
                .orElseThrow(() -> new ShowtimeNotFoundException(showtimeId));
    }
}