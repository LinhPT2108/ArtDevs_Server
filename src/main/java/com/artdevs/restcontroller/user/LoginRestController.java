package com.artdevs.restcontroller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.config.auth.AuthenticationRequest;
import com.artdevs.config.auth.AuthenticationResponse;
import com.artdevs.repositories.user.RoleRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.service.AuthenticationService;
import com.artdevs.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

	@Autowired
	UserRepository userrep;
	@Autowired
	RoleRepository rolerep;
	@Autowired
	private AuthenticationService authenticationService;

	private final JwtTokenProvider jwtTokenUtil;

//	@PostMapping(value = "/api/login")
//	public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
//			@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
//		AuthenticationRequest request = new AuthenticationRequest(email, password);
//		User user = userrep.findByEmail(email).orElseThrow();
//		Map<String, String> tokenMap = new HashMap<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		Role role = null;
//		if (user != null) {
//			role = rolerep.findByUserRole(user);
//		}
//		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		
//		// Tạo token và thêm vào header
//		String token = jwtTokenUtil.generateToken(user, authorities);
//		String refeshToken = jwtTokenUtil.generateRefeshToken(user, authorities);
//
////		// Chuyển hướng đến trang chủ
////		RedirectView redirectView = new RedirectView();
////		redirectView.setUrl("/"); // Điều này giả sử rằng đường dẫn "/" là trang chủ của bạn.
////
////		redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công!");
////		String redirectUrl = "/ArtDevs";
//
////		HttpHeaders headers = new HttpHeaders();
////		headers.add(HttpHeaders.AUTHORIZATION, token);
////		headers.add(HttpHeaders.LOCATION, redirectUrl);
//		tokenMap.put("Token", token);
//		tokenMap.put("RefeshToken", refeshToken);
//		String userJson;
//		try {
//			userJson = objectMapper.writeValueAsString(user);
//			tokenMap.put("User", userJson);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(tokenMap);
//
//		// return new ResponseEntity<>(headers, HttpStatus.FOUND);
//	}
	
//	@PostMapping("/api/login")
//	public ResponseEntity<AuthenticationResponse> login(@RequestParam("email") String email,
//			@RequestParam("password") String password, RedirectAttributes redirectAttributes) {
//		AuthenticationRequest request = new AuthenticationRequest(email, password);
//		return ResponseEntity.ok(authenticationService.authenticate(request));
//
//		// return new ResponseEntity<>(headers, HttpStatus.FOUND);
//	}
	
	@PostMapping(value = "/api/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		System.out.println("hêlolo");
		System.out.println(authenticationService.authenticate(request));
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
	
}
