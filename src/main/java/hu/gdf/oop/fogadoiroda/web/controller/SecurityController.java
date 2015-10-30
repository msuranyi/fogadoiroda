package hu.gdf.oop.fogadoiroda.web.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
public class SecurityController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

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
    public String secured(Model model) {
        model.addAttribute("example", new Example());
        return "welcome";
    }

    @RequestMapping(value = "/postSomething", method = RequestMethod.POST)
    public String postSomething(Model model, Example example) {
        model.addAttribute(example);
        return "example";
    }
}
