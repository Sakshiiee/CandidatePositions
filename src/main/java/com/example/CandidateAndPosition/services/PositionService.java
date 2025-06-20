package com.example.CandidateAndPosition.services;

import com.example.CandidateAndPosition.dtos.PageableResponse;
import com.example.CandidateAndPosition.dtos.PositionDto;

public interface PositionService {

    // Create
    PositionDto create(PositionDto dto);

    // update
    PositionDto update(Long id, PositionDto dto);

    // Partial update
    PositionDto patch(Long id, PositionDto dto);

    // Get all positions
    PageableResponse<PositionDto> getAllPositions(int pageNumber, int pageSize, String sortBy, String sortDir);

    // Get position by ID
    PositionDto getById(Long id);

    // Get position by name
    PositionDto getByPositionName(String name);

    // Delete
    void delete(Long id);
}


