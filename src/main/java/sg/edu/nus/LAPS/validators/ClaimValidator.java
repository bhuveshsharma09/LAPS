package sg.edu.nus.LAPS.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import sg.edu.nus.LAPS.model.Claim;

public class ClaimValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Claim.class.isAssignableFrom(clazz);
        //return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
