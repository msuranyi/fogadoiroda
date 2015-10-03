package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.OsszeadasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class PeldaController {

	@Resource
	private OsszeadasService osszeadasService;

	@RequestMapping("/osszead")
	public String osszzead(Model model, @RequestParam("a") Integer a, @RequestParam("b") Integer b) {
		model.addAttribute("osszeg", osszeadasService.osszead(a, b));
		return "eredmeny";
	}

}
