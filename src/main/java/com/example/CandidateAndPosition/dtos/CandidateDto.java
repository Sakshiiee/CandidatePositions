package com.example.CandidateAndPosition.dtos;

import com.example.CandidateAndPosition.annotations.NullOrNotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateDto {

    private Long id;

    @NullOrNotBlank(min = 1, max = 50, isMandatory = "yes", message = "Candidate name is required and must be between {min} and {max} characters")
    private String name;

    @NullOrNotBlank(min = 5, max = 50, isMandatory = "yes", isEmail = "yes", message = "Valid email address is required and must be between {min} and {max} characters")
    private String email;

    private LocalDate dob;

    private List<Long> positionIds;

    private List<PositionDto> positions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Long> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<Long> positionIds) {
        this.positionIds = positionIds;
    }

    public List<PositionDto> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDto> positions) {
        this.positions = positions;
    }
}
