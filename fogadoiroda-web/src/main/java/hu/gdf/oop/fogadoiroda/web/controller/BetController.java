package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.Event;
import hu.gdf.oop.fogadoiroda.model.Outcome;
import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.service.BetService;
import hu.gdf.oop.fogadoiroda.service.EventService;
import hu.gdf.oop.fogadoiroda.service.OutcomeService;
import hu.gdf.oop.fogadoiroda.service.UserService;
import hu.gdf.oop.fogadoiroda.web.model.Bet;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/bets")
public class BetController {

    @Resource
    private BetService betService;

    @Resource
    private EventService eventService;

    @Resource
    private UserService userService;

    @Resource
    private OutcomeService outcomeService;

    @RequestMapping("events")
    public String listOpenEvents(Model model) {
        model.addAttribute("user", userService.actualUser());
        model.addAttribute("events", eventService.findAllOpen());
        return "bet/event-list";
    }

    @RequestMapping("/list")
    public String listBets(Model model) {
        User user = userService.actualUser();
        model.addAttribute("bets", user.getBets().values());
        return "bet/bet-list";
    }

    @RequestMapping("events/{id}")
    public String eventDetail(Model model, @PathVariable Integer id) {

        Bet bet = new Bet();
        bet.setEventId(id);

        model.addAttribute("event", eventService.findbyId(id));
        model.addAttribute("outcomes", outcomeService.findByBetEventId(id));
        model.addAttribute("bet", bet);

        return "bet/event-detail";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(Model model, @Valid Bet bet, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventService.findbyId(bet.getEventId()));
            return "bet/event-detail";
        }
        betService.bet(bet.getOutcomeId(), bet.getBetAmount());
        return "redirect:/bets/events";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Integer id) {
        betService.delete(id);
        return "redirect:/bets/events";
    }
}
