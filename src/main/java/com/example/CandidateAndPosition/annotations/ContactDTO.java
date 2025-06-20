package com.example.CandidateAndPosition.annotations;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactDTO {
    @NullOrNotBlank( min=1,max=50,isMandatory="yes", message = "Contact Name is required and  must be between {min} and {max} characters")
    private String contactName;

//    @ValidPhoneNumber
//    private String contactMobile;

    @NullOrNotBlank(min = 5, max = 50, isEmail="yes",message = "Valid email address is required and must be between {min} and {max} characters long.")
    private String contactEmail;
}