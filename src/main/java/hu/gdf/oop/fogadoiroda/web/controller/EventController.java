package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
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
import java.util.*;

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
    @RequestMapping("event/{id}/outcome")
    public String editOutcome(Model model, @PathVariable Integer id) {
        Event event = eventService.findbyId(id);
        model.addAttribute("event", event);
        model.addAttribute("closed", event.isClosed());
        model.addAttribute("outcomes", event.getOutcomes());
        model.addAttribute("outcome", new Outcome());
        return "event/outcome";
    }

    @RequestMapping(value = "event/{id}/outcome/add", method = RequestMethod.POST)
    public String addOutcome(Model model,@Valid Outcome outcome, BindingResult result, @PathVariable Integer id) {
        Event event = eventService.findbyId(id);
        if(!event.isClosed()) {
            outcome.setParent(event);
            outcome.setWon(false);
            event.getOutcomes().put(outcome.getId(), outcome);
        }
        return "redirect:/event/{id}/outcome";
    }
    @RequestMapping("event/{id}/outcome/delete")
    public String deleteOutcome(Model model, @PathVariable Integer id, @RequestParam(value = "outcomeId", required = false) Integer outcomeId) {
        Event event = eventService.findbyId(id);
        if(!event.isClosed()) {
            event.getOutcomes().remove(outcomeId);
        }
        return "redirect:/event/{id}/outcome";
    }

    @RequestMapping("event/{id}/outcome/result")
    public String changeOutcomeState(Model model, @PathVariable Integer id, @RequestParam(value = "outcomeId", required = true) Integer outcomeId) {
        Event event = eventService.findbyId(id);
        boolean state = !event.getOutcomes().get(outcomeId).getWon();
        for (Outcome outcome : event.getOutcomes().values()){
            if(outcome.getId().equals(outcomeId)){
                outcome.setWon(state);
            }else{
                if(state == true){
                    outcome.setWon(false);
                }
            }
        }
        return "redirect:/event/{id}/outcome";
    }

    @RequestMapping("event/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
        return "redirect:/events";
    }
    @RequestMapping(value = "event/new-event", method = RequestMethod.POST)
    public String newEvent(@Valid Event event, BindingResult result) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEnd());
        String[] time = event.getEndTime().split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        event.setEnd(calendar.getTime());

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
