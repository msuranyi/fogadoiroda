package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.UserService;
import hu.gdf.oop.fogadoiroda.web.validator.RegistrationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/sign-up")
    public String signUp(Model model) {
        model.addAttribute("registration", new Registration());
        return "user/sign-up";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid Registration registration, BindingResult result) {
        RegistrationValidator validator = new RegistrationValidator();
        validator.validate(registration, result);
        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.register(registration);
        model.addAttribute("username", registration.getUsername());
        return "user/sign-up-success";
    }
}
