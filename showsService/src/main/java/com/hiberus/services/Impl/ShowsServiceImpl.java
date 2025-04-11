package com.hiberus.services.Impl;

import com.hiberus.models.Shows;
import com.hiberus.models.Showtime;
import com.hiberus.repository.ShowsServiceRepository;
import com.hiberus.services.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowsServiceImpl implements ShowsService {

    private final ShowsServiceRepository showsServiceRepository;

    @Autowired
    public ShowsServiceImpl(ShowsServiceRepository showsServiceRepository) {
        this.showsServiceRepository = showsServiceRepository;
    }

    @Override
    public List<Shows> getAllShows() {
        return showsServiceRepository.findAll();
    }

    @Override
    public Shows createShow(Shows show) {
        return showsServiceRepository.save(show);
    }

    @Override
    public Shows getShowById(UUID id) {
        return showsServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with ID: " + id));
    }

    @Override
    public Shows updateShow(UUID id, Shows show) {
        if (!showsServiceRepository.existsById(id)) {
            throw new RuntimeException("Show not found with ID: " + id);
        }
        show.setId(id);
        return showsServiceRepository.save(show);
    }

    @Override
    public void deleteShow(UUID id) {
        if (!showsServiceRepository.existsById(id)) {
            throw new RuntimeException("Show not found with ID: " + id);
        }
        showsServiceRepository.deleteById(id);
    }

    @Override
    public List<Showtime> getShowtimesByShowId(UUID showId) {
        Shows show = showsServiceRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found with ID: " + showId));
        return show.getShowtimes();
    }
}
