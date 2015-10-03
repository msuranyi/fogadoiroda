package hu.gdf.oop.fogadoiroda.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) {
		model.addAttribute("error", error);
		return "auth/login";
	}

	@RequestMapping("/access-denied")
	public String accessDenied() {
		return "auth/access-denied";
	}

	@RequestMapping({"/welcome", "/"})
	public String welcome() {
		return "welcome";
	}

	@RequestMapping("/admin/welcome")
	public String secured() {
		return "welcome";
	}
}
