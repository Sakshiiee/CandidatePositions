package com.example.CandidateAndPosition.dtos;

import com.example.CandidateAndPosition.annotations.NullOrNotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionDto {

    private Long id;

    @NullOrNotBlank(min = 1, max = 50, isMandatory = "yes", message = "Position name is required and must be between {min} and {max} characters")
    private String positionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

//    public PositionDto() {
//    }
//
//    public PositionDto(String positionName, Long id) {
//        this.positionName = positionName;
//        this.id = id;
//    }
}
