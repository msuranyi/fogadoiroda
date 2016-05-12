package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.security.MyUserDetails;
import hu.gdf.oop.fogadoiroda.service.EventService;
import hu.gdf.oop.fogadoiroda.service.OutcomeService;
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

    @Resource
    private OutcomeService outcomeService;

    @RequestMapping("events")
    public String list(Model model) {
        model.addAttribute("operator", SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_OPERATOR")));
        model.addAttribute("list", eventService.findAll());
        return "event/list";
    }
    @RequestMapping("event/editor")
    public String editEvent(Model model, @RequestParam(value = "id", required = false) String id) {
        if(id == null) {
            MyUserDetails userDetails = (MyUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            model.addAttribute("userId", userDetails.getId());
            model.addAttribute("event", new Event());
        }else{
            Event event = eventService.findbyId(Integer.parseInt(id));
            model.addAttribute("userId", event.getUserId());
            model.addAttribute("event", event);
        }
        return "event/editor";
    }
    @RequestMapping("event/{id}/outcome")
    public String editOutcome(Model model, @PathVariable Integer id) {
        Event event = eventService.findbyId(id);
        model.addAttribute("event", event);
        model.addAttribute("closed", event.isClosed());
        model.addAttribute("outcomes", outcomeService.findByBetEventId(id));
        model.addAttribute("outcome", new Outcome());
        return "event/outcome";
    }

    @RequestMapping(value = "event/{id}/outcome/add", method = RequestMethod.POST)
    public String addOutcome(@Valid Outcome outcome, @PathVariable Integer id) {
        Event event = eventService.findbyId(id);
        if(!event.isClosed()) {
            outcome.setEventId(id);
            outcome.setWon(false);
            outcomeService.create(outcome);
        }
        return "redirect:/event/{id}/outcome";
    }
    @RequestMapping("event/{id}/outcome/delete")
    public String deleteOutcome(@PathVariable Integer id, @RequestParam(value = "outcomeId", required = false) Integer outcomeId) {
        Event event = eventService.findbyId(id);
        if(!event.isClosed()) {
            outcomeService.delete(outcomeId);
        }
        return "redirect:/event/{id}/outcome";
    }

    @RequestMapping("event/{id}/outcome/result")
    public String changeOutcomeState(@PathVariable Integer id, @RequestParam(value = "outcomeId", required = true) Integer outcomeId) {
        Event event = eventService.findbyId(id);
        Collection<Outcome> outcomes = outcomeService.findByBetEventId(id);
        for (Outcome o : outcomes){
            if(o.getId().equals(outcomeId)){
                o.setWon(true);
            }else{
                o.setWon(false);
            }
        }
        return "redirect:/event/{id}/outcome";
    }

    @RequestMapping("event/delete/{id}")
    public String deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
        return "redirect:/events";
    }
    @RequestMapping(value = "event/new-event", method = RequestMethod.POST, params="action=save")
    public String newEvent(Model model, @Valid Event event, BindingResult result) {

        processDate(event);
        EventValidator validator = new EventValidator(false);
        validator.validate(event, result);
        if (result.hasErrors()) {
            MyUserDetails userDetails = (MyUserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            model.addAttribute("userId", userDetails.getId());
            return "event/editor";
        }
        if(event.getId() != null && eventService.findbyId(event.getId()) != null){
            eventService.update(event);
        }else {
            eventService.create(event);
        }
        return "redirect:/events";
    }

    @RequestMapping(value = "event/new-event", method = RequestMethod.POST, params="action=publish")
    public String publish(@Valid Event event, BindingResult result) {
        processDate(event);
        EventValidator validator = new EventValidator(true);
        validator.validate(event, result);
        if (result.hasErrors()) {
            return "event/editor";
        }

        event.setStart(new Date());
        eventService.update(event);
        return "redirect:/events";
    }

    private void processDate(Event event) {
        if (event.getStart() != null && event.getStartTime() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getStart());
            String[] time = event.getStartTime().split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            event.setStart(calendar.getTime());
        }
        if (event.getEnd() != null && event.getEndTime() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getEnd());
            String[] time = event.getEndTime().split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            event.setEnd(calendar.getTime());
        }
    }
}
