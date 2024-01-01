package com.artdevs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {

	@GetMapping("/new-test")
	public ResponseEntity<String> getNew(){
		return ResponseEntity.ok("Seuuhf hahg hf !");
	}
}

