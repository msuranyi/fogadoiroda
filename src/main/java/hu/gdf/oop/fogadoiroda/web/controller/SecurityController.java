package hu.gdf.oop.fogadoiroda.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "auth/login";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }

    @RequestMapping({"/welcome", "/"})
    public String welcome(Model model) {
        model.addAttribute("example", new Example());
        return "welcome";
    }

    @RequestMapping("/admin/welcome")
    public String secured() {
        return "welcome";
    }

    @RequestMapping(value = "/postSomething", method = RequestMethod.POST)
    public String postSomething(Model model, Example example) {
        model.addAttribute(example);
        return "example";
    }
}
