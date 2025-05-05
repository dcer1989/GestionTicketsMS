package com.hiberus.usecase;

import com.hiberus.exception.ShowNotFoundException;
import com.hiberus.model.Show;
import com.hiberus.repository.ShowsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateShowUseCase {

    private final ShowsRepository showsRepository;

    public void updateShow(UUID id, Show updatedShow) {

        log.info("Updating show with ID: {}", id);

        showsRepository.findById(id)
                .map(existingShow -> {
                    existingShow.setTitle(updatedShow.getTitle());
                    existingShow.setDescription(updatedShow.getDescription());
                    return showsRepository.save(existingShow);
                })
                .orElseThrow(() -> new ShowNotFoundException(id));
    }
}