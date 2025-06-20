package com.example.CandidateAndPosition.services.impl;

import com.example.CandidateAndPosition.dtos.PageableResponse;
import com.example.CandidateAndPosition.dtos.PositionDto;
import com.example.CandidateAndPosition.entities.Position;
import com.example.CandidateAndPosition.exceptions.ResourceNotFoundException;
import com.example.CandidateAndPosition.helper.Helper;
import com.example.CandidateAndPosition.mapper.PositionMapper;
import com.example.CandidateAndPosition.repositories.PositionRepo;
import com.example.CandidateAndPosition.services.PositionService;
import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepo positionRepo;

//    @Autowired
//    private ModelMapper mapper;

    @Autowired
    private PositionMapper mapper;


    @Override
    public PositionDto create(PositionDto dto) {
        Position position = mapper.toEntity(dto);
        Position saved = positionRepo.save(position);
        return mapper.toDto(saved);
    }

    @Override
    public PositionDto update(Long id, PositionDto dto) {
        Position position = positionRepo.findById(id)                        //fetching position id
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
        mapper.toEntity(dto);                                           //then map to dto-entity
        Position updated = positionRepo.save(position);                      //saving updated
        return mapper.toDto(updated);                       //return updated dto

    }

    @Override
    public PositionDto patch(Long id, PositionDto dto) {
            Position position = positionRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
            if (dto.getPositionName() != null) {
                position.setPositionName(dto.getPositionName());
            }
            return mapper.toDto(positionRepo.save(position));
    }

    @Override
    public PageableResponse<PositionDto> getAllPositions(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Position> page = positionRepo.findAll(pageable);

        return Helper.getPageableResponse(page, mapper::toDto);
    }

        @Override
    public PositionDto getById(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
        return mapper.toDto(position);
    }

    @Override
    public PositionDto getByPositionName(String name) {
        Position position = positionRepo.findByPositionNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with name: " + name));
        return mapper.toDto(position);
    }

    @Override
    public void delete(Long id) {
        Position position = positionRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
        if (position.getCandidates() != null && !position.getCandidates().isEmpty()) {
            throw new RuntimeException("Cannot Delete !!. Position is linked to candidates!");
        }
        positionRepo.delete(position);

    }
}
