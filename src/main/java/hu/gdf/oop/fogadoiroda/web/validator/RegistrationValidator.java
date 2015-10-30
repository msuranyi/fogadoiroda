package hu.gdf.oop.fogadoiroda.web.validator;

import hu.gdf.oop.fogadoiroda.web.model.Registration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Registration reg = (Registration) target;

        if (reg.getPassword() != null && !reg.getPassword().equals(reg.getPasswordCheck())) {
            errors.rejectValue("passwordCheck", null, "must equals with password");
        }
    }
}
