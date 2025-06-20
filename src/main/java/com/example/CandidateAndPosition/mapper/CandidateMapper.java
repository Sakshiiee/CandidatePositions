package com.example.CandidateAndPosition.mapper;

import com.example.CandidateAndPosition.dtos.CandidateDto;
import com.example.CandidateAndPosition.entities.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateDto toDto(Candidate candidate);

    Candidate toEntity(CandidateDto candidateDto);
}
