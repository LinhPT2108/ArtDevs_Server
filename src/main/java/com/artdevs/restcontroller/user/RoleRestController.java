package com.artdevs.restcontroller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Role;
import com.artdevs.dto.user.RoleDTO;
import com.artdevs.mapper.RoleMapper;
import com.artdevs.repositories.user.RoleRepository;
import com.artdevs.services.impl.transition.RoleServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class RoleRestController {

	@Autowired
	RoleServiceImpl roleServiceImpl;

	@GetMapping("/role")
	public ResponseEntity<List<Role>> getRole() {
		return ResponseEntity.ok(roleServiceImpl.findAll());
	}

	@PostMapping("/role")
	public ResponseEntity<Role> postRole(@RequestBody RoleDTO roleDTO) {
		return ResponseEntity.ok(roleServiceImpl.saveRole(RoleMapper.convertToRole(roleDTO)));
	}
}
