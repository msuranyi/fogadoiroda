package hu.gdf.oop.fogadoiroda.web.controller;

import hu.gdf.oop.fogadoiroda.service.IntegrationService;
import hu.gdf.oop.fogadoiroda.xml.OpenEventsResponse;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ServiceController {

	@Resource
	private IntegrationService integrationService;

	@RequestMapping("echo/{message}")
	public String echo(@PathVariable("message") String message) {
		return "Echo: " + message;
	}

	@RequestMapping("openEvents")
	public OpenEventsResponse openEvents() {
		return integrationService.openEvents();
	}

}