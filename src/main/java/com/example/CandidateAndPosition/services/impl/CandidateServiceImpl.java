package com.example.CandidateAndPosition.services.impl;

import com.example.CandidateAndPosition.dtos.CandidateDto;
import com.example.CandidateAndPosition.dtos.PageableResponse;
import com.example.CandidateAndPosition.entities.Candidate;
import com.example.CandidateAndPosition.entities.Position;
import com.example.CandidateAndPosition.exceptions.ResourceNotFoundException;
import com.example.CandidateAndPosition.helper.Helper;
import com.example.CandidateAndPosition.mapper.CandidateMapper;
import com.example.CandidateAndPosition.repositories.CandidateRepo;
import com.example.CandidateAndPosition.repositories.PositionRepo;
import com.example.CandidateAndPosition.services.CandidateService;
//import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepo candidateRepo;

    private final PositionRepo positionRepo;

    public CandidateServiceImpl(CandidateRepo candidateRepo, PositionRepo positionRepo) {
        this.candidateRepo = candidateRepo;
        this.positionRepo = positionRepo;
    }

//    @Autowired
//    private ModelMapper mapper;

    @Autowired
    private CandidateMapper mapper;


    @Override
    public CandidateDto create(CandidateDto dto) {
        age(dto.getDob());
        Candidate candidate = mapper.toEntity(dto);
        candidate.setPositions(getPositionEntities(dto.getPositionIds()));
        Candidate saved = candidateRepo.save(candidate);
        return mapper.toDto(saved);
    }

    @Override
    public CandidateDto update(Long id, CandidateDto dto) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));

        age(dto.getDob());
        mapper.toEntity(dto);
        candidate.setPositions(getPositionEntities(dto.getPositionIds()));
        Candidate updated = candidateRepo.save(candidate);
        return mapper.toDto(updated);
    }

    @Override
    public CandidateDto patch(Long id, CandidateDto dto) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));

        age(dto.getDob());
        mapper.toEntity(dto);
        candidate.setPositions(getPositionEntities(dto.getPositionIds()));
        Candidate updated = candidateRepo.save(candidate);
        return mapper.toDto(updated);
    }

    @Override
    public CandidateDto getById(Long id) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
        return mapper.toDto(candidate);
    }

    @Override
    public CandidateDto getByEmail(String email) {
        Candidate candidate = candidateRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with email: " + email));
        return mapper.toDto(candidate);
    }

    @Override
    public List<CandidateDto> search(String keyword) {
        List<Candidate> candidates = candidateRepo.findByNameContainingIgnoreCase(keyword);
        return candidates.stream()
                .map(candidate -> mapper.toDto(candidate))
                .toList();
    }

    @Override
    public PageableResponse<CandidateDto> getAllCandidates(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Candidate> candidatePage = candidateRepo.findAll(pageable);

        return Helper.getPageableResponse(candidatePage, mapper::toDto);
    }


//    @Override
//    public PageableResponse<CandidateDto> getAllCandidates(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
//        Page<Candidate> page = candidateRepo.findAll(pageable);
//        return Helper.getPageableResponse(page, CandidateDto.class);
//    }

    @Override
    public void delete(Long id) {
        Candidate candidate = candidateRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
        candidateRepo.delete(candidate);

    }

    //for age validation

    private void age(LocalDate dob) {
        if (dob == null || Period.between(dob, LocalDate.now()).getYears() < 18) {
            throw new RuntimeException("Candidate must me 18 or more than 18years old !!");
        }
    }

    private List<Position> getPositionEntities(List<Long> positionIds) {
        return positionIds.stream()
                .map(id -> positionRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id)))
                .collect(Collectors.toList());

    }
}