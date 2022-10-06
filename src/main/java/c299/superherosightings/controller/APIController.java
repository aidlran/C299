package c299.superherosightings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import c299.superherosightings.service.ServiceLayer;

@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	private ServiceLayer service;
}
