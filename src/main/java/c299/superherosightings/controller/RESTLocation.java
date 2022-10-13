package c299.superherosightings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c299.superherosightings.dto.Location;
import c299.superherosightings.service.ServiceLayer;

@RestController
@RequestMapping("/api/location")
public class RESTLocation {

	@Autowired
	private ServiceLayer serviceLayer;

	@GetMapping("{id}")
	public Location getLocation(@PathVariable int id) {
		return serviceLayer.getLocation(id);
	}
}
