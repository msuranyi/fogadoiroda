package hu.gdf.oop.fogadoiroda.web.validator;


import hu.gdf.oop.fogadoiroda.model.Event;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {


    }
}
