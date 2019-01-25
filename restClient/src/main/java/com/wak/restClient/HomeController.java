package com.wak.restClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;

	@GetMapping
	public String sendGreeting() {
		return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
	}
	
	@RequestMapping("/{id}")
	public Gallery getGallery(@PathVariable final int id) {
		// create gallery object
		Gallery gallery = new Gallery();
		gallery.setId(id);
		
		// get list of available images 
		List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
		gallery.setImages(images);
	
		return gallery;
	}
}
