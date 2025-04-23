package com.hiberus.repository;

import com.hiberus.models.Show;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ShowsServiceRepository extends JpaRepository<Show, UUID> {

}
