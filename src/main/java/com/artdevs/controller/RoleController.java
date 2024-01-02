package com.artdevs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Role;
import com.artdevs.repositories.user.RoleRepository;

@RestController
public class RoleController {
	
	@Autowired RoleRepository roleRepository;
	
	@GetMapping("/role")
	public ResponseEntity<List<Role>> getRole(){
		return ResponseEntity.ok(roleRepository.findAll());
	}
}

