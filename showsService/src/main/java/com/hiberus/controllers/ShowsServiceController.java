package com.hiberus.controllers;

import com.hiberus.dto.ShowsDto;
import com.hiberus.dto.ShowtimeDto;
import com.hiberus.models.Shows;
import com.hiberus.models.Showtime;
import com.hiberus.services.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/shows")
public class ShowsServiceController {

    private final ShowsService showsService;

    @Autowired
    public ShowsServiceController(ShowsService showsService) {
        this.showsService = showsService;
    }

    @GetMapping
    public ResponseEntity<List<ShowsDto>> getAllShows() {
        List<ShowsDto> showsDtos = showsService.getAllShows().stream()
                .map(this::mapToShowsDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(showsDtos);
    }

    @PostMapping
    public ResponseEntity<ShowsDto> createShow(@RequestBody Shows show) {
        Shows createdShow = showsService.createShow(show);
        return ResponseEntity.status(201).body(mapToShowsDto(createdShow));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowsDto> getShowById(@PathVariable UUID id) {
        Shows show = showsService.getShowById(id);
        return ResponseEntity.ok(mapToShowsDto(show));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowsDto> updateShow(@PathVariable UUID id, @RequestBody Shows show) {
        Shows updatedShow = showsService.updateShow(id, show);
        return ResponseEntity.ok(mapToShowsDto(updatedShow));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable UUID id) {
        showsService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{showId}/showtimes")
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByShowId(@PathVariable UUID showId) {
        List<ShowtimeDto> showtimeDtos = showsService.getShowtimesByShowId(showId).stream()
                .map(this::mapToShowtimeDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(showtimeDtos);
    }

    private ShowsDto mapToShowsDto(Shows show) {
        return ShowsDto.builder()
                .id(show.getId())
                .title(show.getTitle())
                .description(show.getDescription())
                .showtimes(show.getShowtimes().stream()
                        .map(this::mapToShowtimeDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private ShowtimeDto mapToShowtimeDto(Showtime showtime) {
        return ShowtimeDto.builder()
                .room(showtime.getRoom())
                .times(showtime.getTime())
                .build();
    }
}
