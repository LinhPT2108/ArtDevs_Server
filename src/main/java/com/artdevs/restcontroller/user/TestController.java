package com.artdevs.restcontroller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/api/get-login")
	public ResponseEntity<Authentication> getMethodName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok(authentication);
	}
	
	@GetMapping("/api/cart")
	public ResponseEntity<?> getMethodName1() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ResponseEntity.ok(404);
	}
	@GetMapping("/api/statistical-order")
	public ResponseEntity<?> getMethodName2() {
		return ResponseEntity.ok(200);
	}
}
