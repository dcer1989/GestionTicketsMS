package com.hiberus.services;

import com.hiberus.models.Shows;
import com.hiberus.models.Showtime;

import java.util.List;
import java.util.UUID;

public interface ShowsService {
    List<Shows> getAllShows();
    Shows createShow(Shows show);
    Shows getShowById(UUID id);
    Shows updateShow(UUID id, Shows show);
    void deleteShow(UUID id);
    List<Showtime> getShowtimesByShowId(UUID showId);
}
