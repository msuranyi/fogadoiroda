package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.EventService;
import hu.gdf.oop.fogadoiroda.web.model.EventReg;
import hu.gdf.oop.fogadoiroda.web.validator.EventValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class EventController {

    @Resource
    private EventService eventService;

    @RequestMapping("events")
    public String list(Model model) {
        model.addAttribute("operator", SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_OPERATOR")));
        model.addAttribute("list", eventService.findAll());
        return "event/list";
    }
    @RequestMapping("event/editor")
    public String editEvent(Model model) {
        model.addAttribute("eventreg", new EventReg());
        return "event/editor";
    }
    @RequestMapping(value = "event/new-event", method = RequestMethod.POST)
    public String register(Model model, @Valid EventReg eventReg, BindingResult result) {
        EventValidator validator = new EventValidator();
        validator.validate(eventReg, result);
        if (result.hasErrors()) {
            return "event/editor";
        }
        eventService.create(eventReg.getInstance());
        return "events";
    }
}
