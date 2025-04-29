package com.hiberus.usecase;

import com.hiberus.model.Show;
import com.hiberus.repository.ShowsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateShowUseCase {

    private final ShowsRepository showsRepository;

    public void createShow(Show show) {

        log.info("Creating a new show with title: {}", show.getTitle());

        showsRepository.save(show);
    }
}