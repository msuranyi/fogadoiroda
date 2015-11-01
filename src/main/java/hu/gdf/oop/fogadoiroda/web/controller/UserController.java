package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.service.UserService;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import hu.gdf.oop.fogadoiroda.web.model.Registration;
import hu.gdf.oop.fogadoiroda.web.validator.ProfileValidator;
import hu.gdf.oop.fogadoiroda.web.validator.RegistrationValidator;
import org.springframework.security.core.context.SecurityContextHolder;
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
            return "user/sign-up";
        }
        userService.register(registration);
        model.addAttribute("username", registration.getUsername());
        return "user/sign-up-success";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByName(username);
        Profile profile = new Profile(user.getUsername(), user.getEmail());
        model.addAttribute(profile);
        return "user/profile";
    }

    @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
    public String updateProfile(Model model, @Valid Profile profile, BindingResult result) {

        ProfileValidator validator = new ProfileValidator(userService);
        validator.validate(profile, result);

        if (result.hasErrors()) {
            return "user/profile";
        }

        userService.update(profile);

        profile.clear();
        model.addAttribute(profile);
        model.addAttribute("message", "A profil módosítása sikeresen megtörtént");

        return "user/profile";
    }

    @RequestMapping("users")
    public String list(Model model) {
        model.addAttribute("list", userService.findAll());
        return "user/list";
    }
}
