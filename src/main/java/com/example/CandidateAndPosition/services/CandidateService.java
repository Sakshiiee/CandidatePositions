package com.example.CandidateAndPosition.services;

import com.example.CandidateAndPosition.dtos.CandidateDto;
import com.example.CandidateAndPosition.dtos.PageableResponse;

import java.util.List;

public interface CandidateService {
    // Create
    CandidateDto create(CandidateDto dto);

    // Full update
    CandidateDto update(Long id, CandidateDto dto);

    // Partial update
    CandidateDto patch(Long id, CandidateDto dto);

    // Get candidate by ID
    CandidateDto getById(Long id);

    // Get candidate by email
    CandidateDto getByEmail(String email);

    // Search candidates by name
    List<CandidateDto> search(String keyword);

    // Get all candidates with pagination
    PageableResponse<CandidateDto> getAllCandidates(int pageNumber, int pageSize, String sortBy, String sortDir);

    // Delete candidate
    void delete(Long id);


}
