package hu.gdf.oop.fogadoiroda.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.gdf.oop.fogadoiroda.service.OsszeadasService;

/**
 * Spring-MVC Controller osztály, példákkal.
 */
@Controller
public class PeldaController {

	/**
	 * Üzleti logikát (jelen esetben az összeadás matematikai műveletet)
	 * megvalósító komponens. A Resource annotáció miatt a Spring Framework
	 * megkeresi az interfészt implementáló osztályt és példányosítva beteszi
	 * ide.
	 */
	@Resource
	private OsszeadasService osszeadasService;

	/**
	 * HTTP kéréseket kiszolgáló metódus.
	 * <br/>
	 * A kérés akkor fog ebbe a metódusba érkezni, ha az URL ".../osszead"
	 * formában van megadva (pl. "http://localhost:8080/fogadoiroda/osszead")
	 * 
	 * @param model
	 *            ebbe az objektumba kell belepakolni minden olyat, amire
	 *            szükség lesz a view generálásakor
	 * @param a
	 *            HTTP paraméter, tehát az URL-nek ".../osszead?a=integer"
	 *            formájúnak kell lennie. A típuskonverziót elvégzi a Spring-MVC
	 * @param b
	 *            HTTP paraméter, tehát az URL-nek ".../osszead?a=integer"
	 *            formájúnak kell lennie. A típuskonverziót elvégzi a Spring-MVC
	 * @return annak a view-nak a neve, amely a kérésre adott választ generálni
	 *         fogja.<br/>
	 *         A jelenlegi konfiguráció szerint ez a
	 *         webapp/WEB-INF/templates/eredmeny.html fájl lesz
	 */
	@RequestMapping("/osszead")
	public String osszzead(Model model, @RequestParam("a") Integer a, @RequestParam("b") Integer b) {
		model.addAttribute("osszeg", osszeadasService.osszead(a, b));
		return "eredmeny";
	}

}
