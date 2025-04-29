package com.hiberus.usecase;

import com.hiberus.model.Show;
import com.hiberus.repository.ShowsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllShowsUseCase {

    private final ShowsRepository showsRepository;

    public List<Show> getAllShows() {

        log.info("Use case to get all shows");

        return showsRepository.findAll();
    }
}