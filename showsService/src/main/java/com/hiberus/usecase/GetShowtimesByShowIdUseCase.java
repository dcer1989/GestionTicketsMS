package com.hiberus.usecase;

import com.hiberus.model.Showtime;
import com.hiberus.repository.ShowtimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetShowtimesByShowIdUseCase {

    private final ShowtimeRepository showtimeRepository;

    public List<Showtime> getShowtimesByShowId(UUID showId) {

        log.info("Getting showtimes for show with ID: {}", showId);

        return showtimeRepository.findByShowId(showId);
    }
}