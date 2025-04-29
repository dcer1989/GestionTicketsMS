package com.hiberus.repository;

import com.hiberus.model.Show;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ShowsRepository extends JpaRepository<Show, UUID> {

}
