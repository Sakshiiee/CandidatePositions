package com.example.CandidateAndPosition.repositories;

import com.example.CandidateAndPosition.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {


    Optional<Candidate> findByEmail(String email);

    List<Candidate> findByNameContainingIgnoreCase(String name);
}
