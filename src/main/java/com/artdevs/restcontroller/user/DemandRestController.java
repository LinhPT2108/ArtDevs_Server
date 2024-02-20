package com.artdevs.restcontroller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.DemandDTO;
import com.artdevs.mapper.DemandMapper;
import com.artdevs.services.DemandService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class DemandRestController {
	@Autowired
	DemandService demandService;

	@Autowired
	UserService userService;

	@PostMapping("/demand")
	public ResponseEntity<Demand> postDemand(@RequestBody DemandDTO demandDTO) {
		return ResponseEntity.ok(demandService.saveDemand(DemandMapper.convertToDemand(demandDTO)));
	}

	@GetMapping("/demand")
	public ResponseEntity<List<DemandDTO>> getDemand() {
		List<DemandDTO> listDemandDTO = new ArrayList<>();
		List<Demand> listDemand = demandService.findAll();
		for (Demand demand : listDemand) {
			listDemandDTO.add(DemandMapper.convertToDemandDTO(demand));
		}
		return ResponseEntity.ok(listDemandDTO);
	}

	@GetMapping("/demands-by-user-logged")
	public ResponseEntity<?> getDemandByUserLogged() {

		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		if (!authenticate.getName().equals("anonymousUser")) {
			User user = userService.findByEmail(authenticate.getName());
			List<Demand> demands = demandService.findByUser(user);
			return ResponseEntity
					.ok(demands.stream().map(t -> DemandMapper.convertToDemandDTO(t)).collect(Collectors.toList()));
		} else {
			return ResponseEntity.ok(HttpStatus.SC_UNAUTHORIZED);
		}
	}

}
