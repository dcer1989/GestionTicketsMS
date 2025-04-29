package com.hiberus.usecase;
import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.repository.ShowsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteShowUseCase {

    private final ShowsRepository showsRepository;

    public void deleteShow(UUID showId) {

        log.info("Deleting show with ID: {}", showId);

        if (!showsRepository.existsById(showId)) {
            throw new ShowNotFoundException(showId);
        }

        showsRepository.deleteById(showId);
    }
}