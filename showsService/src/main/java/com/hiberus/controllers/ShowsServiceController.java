package com.hiberus.controllers;

import com.hiberus.dto.ShowtimeDto;
import com.hiberus.models.Show;
import com.hiberus.dto.ShowDto;
import com.hiberus.services.ShowsService;
import com.hiberus.utils.ShowMapper;
import com.hiberus.utils.ShowtimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<ShowDto>> getAllShows() {
        List<ShowDto> showsDtos = showsService.getAllShows().stream()
                .map(ShowMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(showsDtos);
    }

    @PostMapping
    public ResponseEntity<ShowDto> createShow(@RequestBody Show show) {
        Show createdShow = showsService.createShow(show);
        return ResponseEntity.status(201).body(ShowMapper.toDto(createdShow));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable UUID id) {
        Show show = showsService.getShowById(id);
        return ResponseEntity.ok(ShowMapper.toDto(show));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowDto> updateShow(@PathVariable UUID id, @RequestBody Show show) {
        Show updatedShow = showsService.updateShow(id, show);
        return ResponseEntity.ok(ShowMapper.toDto(updatedShow));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable UUID id) {
        showsService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{showId}/showtimes")
    public ResponseEntity<List<ShowtimeDto>> getShowtimesByShowId(@PathVariable UUID showId) {
        List<ShowtimeDto> showtimeDtos = showsService.getShowtimesByShowId(showId).stream()
                .map(ShowtimeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(showtimeDtos);
    }

    @GetMapping("/{showId}/showtimes/{showtimeId}")
    public ResponseEntity<ShowtimeDto> getShowtimeById(
            @PathVariable UUID showId,
            @PathVariable Integer showtimeId) {
        Show show = showsService.getShowById(showId); // Retrieve the Show object
        Optional<ShowtimeDto> showtimeDto = show.getShowtimes().stream()
                .filter(showtime -> showtime.getId().equals(showtimeId)) // Access the id field
                .map(ShowtimeMapper::toDto)
                .findFirst();

        return showtimeDto
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
