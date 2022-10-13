package c299.superherosightings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import c299.superherosightings.dto.Sighting;
import c299.superherosightings.service.ServiceLayer;

@RestController
@RequestMapping("/api/sighting")
public class RESTSighting {

	@Autowired
	private ServiceLayer serviceLayer;

	@GetMapping("{id}")
	public Sighting getCharacter(@RequestParam int id) {
		return serviceLayer.getSighting(id);
	}

	@GetMapping("recent")
	public List<Sighting> getRecentSightings() {
		return serviceLayer.getRecentSightings();
	}
}
