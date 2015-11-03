package hu.gdf.oop.fogadoiroda.web.validator;


import hu.gdf.oop.fogadoiroda.web.model.EventReg;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Dio on 2015.11.03..
 */
public class EventValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return EventReg.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {


    }
}
