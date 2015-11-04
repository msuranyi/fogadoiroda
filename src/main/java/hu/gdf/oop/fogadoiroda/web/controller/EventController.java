package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.service.EventService;
import hu.gdf.oop.fogadoiroda.web.validator.EventValidator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

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
    public String editEvent(Model model, @RequestParam(value = "id", required = false) String id) {
        if(id == null) {
            model.addAttribute("event", new Event());
        }else{
            Event event = eventService.findbyId(Integer.parseInt(id));
            model.addAttribute("event", event);
        }
        return "event/editor";
    }
    @RequestMapping("event/delete/{id}")
    public String deleteEvent(Model model, @PathVariable Integer id) {
        eventService.delete(id);
        return "redirect:/events";
    }
    @RequestMapping(value = "event/new-event", method = RequestMethod.POST)
    public String newEvent(Model model, @Valid Event event, BindingResult result) {
        EventValidator validator = new EventValidator();
        validator.validate(event, result);
        if (result.hasErrors()) {
            return "event/editor";
        }
        if(eventService.findbyId(event.getId()) != null){
            eventService.update(event);
        }else {
            event.setStart(new Date());
            eventService.create(event);
        }
        return "redirect:/events";
    }
}
