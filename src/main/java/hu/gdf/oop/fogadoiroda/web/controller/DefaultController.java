package hu.gdf.oop.fogadoiroda.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@ControllerAdvice
public class DefaultController {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(text));
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                String result = null;
                if (getValue() != null) {
                    result = new SimpleDateFormat("yyyy.MM.dd HH:mm").format((Date) getValue());
                }
                return result;
            }
        });
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest req, Throwable exception) {

        LOG.error("Alkalmazás hiba lépett fel!", exception);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.addObject("statusCode", 500);
        modelAndView.setViewName("error/short");

        return modelAndView;
    }

    @RequestMapping({"/welcome", "/"})
    public String welcome() {
        return "welcome";
    }
}
