package com.example.CandidateAndPosition.repositories;

import com.example.CandidateAndPosition.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepo extends JpaRepository<Position, Long> {

    Optional<Position> findByPositionNameIgnoreCase(String positionName);
}
