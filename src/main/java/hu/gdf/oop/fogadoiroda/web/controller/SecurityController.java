package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class SecurityController {

    @Resource
    private UserRepository userRepository;

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

    @RequestMapping(value = "/sign-up")
    public String signUp(Model model) {
        model.addAttribute("registration", new Registration());
        return "sign-up";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Registration registration) {
        userRepository.create(new User(registration.getUsername(), registration.getPassword(), "USER"));
        return "redirect:/welcome";
    }
}
