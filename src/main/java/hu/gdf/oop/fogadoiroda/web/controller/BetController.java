package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.BetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/bets")
public class BetController {

    @Resource
    private BetService betService;

    @RequestMapping("events")
    public String listOpenEvents(Model model) {
        model.addAttribute("events", betService.findOpenEvents());
        return "bet/event-list";
    }

    @RequestMapping("events/{id}")
    public String eventDetail(Model model, @PathVariable Integer id) {

        model.addAttribute("event", betService.findOne(id));
        return "bet/event-detail";
    }

}
