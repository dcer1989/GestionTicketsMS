package com.hiberus.controller;

import com.hiberus.dto.ShowRequest;
import com.hiberus.dto.ShowResponse;
import com.hiberus.dto.ShowtimeResponse;
import com.hiberus.model.Show;
import com.hiberus.model.Showtime;
import com.hiberus.usecase.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShowsRestController {

    private final GetAllShowsUseCase getAllShowsUseCase;
    private final CreateShowUseCase createShowUseCase;
    private final GetShowByIdUseCase getShowByIdUseCase;
    private final UpdateShowUseCase updateShowUseCase;
    private final DeleteShowUseCase deleteShowUseCase;
    private final GetShowtimesByShowIdUseCase getShowtimesByShowIdUseCase;
    private final GetShowtimeByShowShowtimeIdsUseCase getShowtimeByShowShowtimeIdsUseCase;
    private final com.hiberus.mapper.ShowMapper showMapper;
    private final com.hiberus.mapper.ShowtimeMapper showtimeMapper;

    @GetMapping("/v1/shows")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowResponse> getAllShows() {

        log.info("Request received to fetch all shows");

        return getAllShowsUseCase.getAllShows().stream()
                .map(showMapper::toDto)
                .toList();
    }

    @PostMapping("/v1/shows")
    @ResponseStatus(HttpStatus.CREATED)
    public void createShow(@RequestBody ShowRequest showRequest) {

        log.info("Creating a new show with title: {}", showRequest.title());

        createShowUseCase.createShow(showMapper.toEntity(showRequest));
    }

    @GetMapping("/v1/shows/{showId}")
    @ResponseStatus(HttpStatus.OK)
    public ShowResponse getShowById(@PathVariable UUID showId) {

        log.info("Fetching show with ID: {}", showId);

        return showMapper.toDto(getShowByIdUseCase.getShowById(showId));
    }

    @PutMapping("/v1/shows/{showId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateShow(@PathVariable UUID showId, @RequestBody ShowRequest showRequest) {

        log.info("Request received to update show with ID: {}", showId);

        updateShowUseCase.updateShow(showId, showMapper.toEntity(showRequest));
    }

    @DeleteMapping("/v1/shows/{showId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShow(@PathVariable UUID showId) {

        log.info("Request received to delete show with ID: {}", showId);

        deleteShowUseCase.deleteShow(showId);
    }

    @GetMapping("/v1/shows/{showId}/showtimes")
    @ResponseStatus(HttpStatus.OK)
    public List<ShowtimeResponse> getShowtimesByShowId(@PathVariable UUID showId) {

        log.info("Fetching showtimes for show with ID: {}", showId);

        return getShowtimesByShowIdUseCase.getShowtimesByShowId(showId).stream()
                .map(showtimeMapper::toDto)
                .toList();
    }

    @GetMapping("/v1/shows/{showId}/showtimes/{showtimeId}")
    @ResponseStatus(HttpStatus.OK)
    public ShowtimeResponse getShowtimeById(
            @PathVariable UUID showId,
            @PathVariable UUID showtimeId) {

        log.info("Fetching showtime with show ID: {} and showtime ID: {}", showId, showtimeId);

        return showtimeMapper.toDto(getShowtimeByShowShowtimeIdsUseCase.getShowtimeByShowShowtimeIds(showId, showtimeId));
    }
}