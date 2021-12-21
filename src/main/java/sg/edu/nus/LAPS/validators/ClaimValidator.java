package sg.edu.nus.LAPS.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.nus.LAPS.model.Claim;

@Component
public class ClaimValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Claim.class.isAssignableFrom(clazz);
        //return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Claim claim = (Claim) target;
        ValidationUtils.rejectIfEmpty(errors,"overtimeDate","error.claim.overtimeDate.empty");

        ValidationUtils.rejectIfEmpty(errors,"hoursWorked","error.claim.hoursWorked.empty");

        ValidationUtils.rejectIfEmpty(errors,"eligibleClaim","error.claim.eligibleClaim.empty");

        System.out.println(claim.toString());



    }
}
