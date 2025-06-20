package com.example.CandidateAndPosition.mapper;

import com.example.CandidateAndPosition.dtos.PositionDto;

import com.example.CandidateAndPosition.entities.Position;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

    PositionDto toDto(Position position);

    Position toEntity(PositionDto positionDto);
}
