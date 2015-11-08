package hu.gdf.oop.fogadoiroda.web.validator;


import hu.gdf.oop.fogadoiroda.model.Event;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Esemény létrehozást ellenőrző validátor komponens.
 */
public class EventValidator implements Validator {

    private boolean publish;

    public EventValidator(boolean publish) {
        this.publish = publish;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;

        if (publish) {
            ValidationUtils.rejectIfEmpty(errors, "end", "Required");
            ValidationUtils.rejectIfEmpty(errors, "endTime", "Required");
        }

        if (event.getEnd() != null && event.getEndTime() != null && event.getEnd().before(new Date())) {
            errors.rejectValue("end", "Future");
        }
    }
}
