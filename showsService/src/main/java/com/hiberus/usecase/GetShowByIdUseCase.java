package com.hiberus.usecase;

import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.exception.ShowtimeNotFoundException;
import com.hiberus.model.Show;
import com.hiberus.repository.ShowsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetShowByIdUseCase {

    private final ShowsRepository showsRepository;

    public Show getShowById(UUID showId) {

        log.info("Getting show with ID: {}", showId);

        return showsRepository.findById(showId)
                .orElseThrow(() -> new ShowNotFoundException(showId));
    }
}
