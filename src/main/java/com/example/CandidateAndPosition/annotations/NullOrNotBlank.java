package com.example.CandidateAndPosition.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "Null or Blank value not allowed";
    Class<?>[] groups() default {};
    int min() default 0;
	int max() default 0;
	String isMandatory() default "yes";
    String isEmail() default "no";
    Class<? extends Payload>[] payload() default {};
}
