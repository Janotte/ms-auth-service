package br.net.supptech.authservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CellPhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CellPhone {
     
    String message() default "Does not appear to be a valid phone number";
     
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
      
}