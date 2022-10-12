package c299.superherosightings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c299.superherosightings.dto.Sighting;
import c299.superherosightings.service.ServiceLayer;

@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	private ServiceLayer serviceLayer;

	@GetMapping("sighting/recent")
	public List<Sighting> getRecentSightings() {
		return serviceLayer.getRecentSightings();
	}
}
