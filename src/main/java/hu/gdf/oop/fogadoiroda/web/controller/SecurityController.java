package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.UserService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@ControllerAdvice
public class SecurityController {

    @Resource
    private UserService userService;

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

    @RequestMapping(value = "/sign-up")
    public String signUp(Model model) {
        model.addAttribute("registration", new Registration());
        return "sign-up";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid Registration registration, BindingResult result) {
        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.register(registration);
        model.addAttribute("username", registration.getUsername());
        return "sign-up-success";
    }
}
