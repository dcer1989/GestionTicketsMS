package com.hiberus.services;

import com.hiberus.models.Show;
import com.hiberus.models.Showtime;

import java.util.List;
import java.util.UUID;

public interface ShowsService {
    List<Show> getAllShows();
    Show createShow(Show show);
    Show getShowById(UUID id);
    Show updateShow(UUID id, Show show);
    void deleteShow(UUID id);
    List<Showtime> getShowtimesByShowId(UUID showId);
}
