package br.net.supptech.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CellPhoneValidator implements ConstraintValidator<CellPhone, String> {

    @Override
    public void initialize(CellPhone paramA) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if(phoneNo == null){
            return false;
        }
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{11}")) return true;
            //validating phone number with -, . or spaces
        else //return false if nothing matches the input
            if(phoneNo.matches("\\d{3}' '\\d{5}[- ]\\d{4}")) return true;
            //validating phone number where area code is in braces ()
        else return phoneNo.matches("\\(\\d{3}\\)' '\\d{5}[- ]\\d{4}");
    }
}
