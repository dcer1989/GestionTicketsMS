package com.hiberus.repository;

import com.hiberus.models.Shows;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ShowsServiceRepository extends JpaRepository<Shows, UUID> {
    // Custom query methods can be defined here
    // For example:
    // List<ShowsService> findByTitle(String title);
}
