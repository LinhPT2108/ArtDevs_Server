package com.artdevs.restcontroller.user;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.config.auth.AuthenticationRequest;
import com.artdevs.config.auth.AuthenticationResponse;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.ErrorResponseDTO;
import com.artdevs.dto.MailInfo;
import com.artdevs.dto.ReponseDTO;
import com.artdevs.dto.CustomDTO.ChangePasswordFormDTO;
import com.artdevs.dto.CustomDTO.VerificationDTO;
import com.artdevs.service.AuthenticationService;
import com.artdevs.service.JwtTokenProvider;
import com.artdevs.service.MailerService;
import com.artdevs.services.UserService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginRestController {

	@Autowired
	UserService userservice;
	
	
	@Autowired MailerService mailservice;
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private final AuthenticationManager authenticationManager;
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
		try {
			return ResponseEntity.ok(authenticationService.authenticate(request));
		} catch (Exception e) {
			e.printStackTrace();

			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping(value = "/api/logout")
	public ResponseEntity<?> logout() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userservice.findByEmail(auth.getName());
		user.setIsOnline(false);
		userservice.saveUser(user);

//		String authorizationHeader = request.getHeader(AUTHORIZATION);
//		String token = authorizationHeader.substring("Bearer ".length());
		System.out.println("Logout token: " + user);
//		jwtTokenUtil.deleteToken(token);
		return ResponseEntity.ok(true);
	}

	@PostMapping(value = "/api/changepass")
	public ResponseEntity<ErrorResponseDTO> ChangePass(@RequestBody ChangePasswordFormDTO passwordForm) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userservice.findByEmail(auth.getName());
	    ErrorResponseDTO statusReponse = new ErrorResponseDTO();
	    // Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
	    String hashedPasswordFromDatabase = user.getPassword();

	    String rawOldPassword = passwordForm.getOldPassword();
	    
	    try {
	        // So sánh mật khẩu cũ
	        if (passwordEncoder.matches(rawOldPassword, hashedPasswordFromDatabase)) {
	            // Mật khẩu cũ đúng, bạn có thể thực hiện các bước thay đổi mật khẩu mới ở đây
	            // ...
	        	user.setPassword(new BCryptPasswordEncoder().encode(passwordForm.getNewPassword()));
	        	
	        	userservice.saveUser(user);
	        	
	        	statusReponse.setErrorCode(200);
	        	statusReponse.setMessage("Đổi Mật Khẩu Thành Công!");
	        	
	           
	        } else {
	        	
	            // Mật khẩu cũ không đúng
	        	statusReponse.setErrorCode(400);
	        	statusReponse.setMessage("Mật Khẩu Không Khớp!");
	            
	        }
	    } catch (Exception e) {
	        // Xử lý ngoại lệ khi so sánh mật khẩu
	        e.printStackTrace();
	        statusReponse.setErrorCode(500);
        	statusReponse.setMessage("Lỗi Ngoại Lệ!");
	       
	    }
	    return ResponseEntity.ok(statusReponse);
	}
	
	@PostMapping(value = "/api/send-verify")
	public ResponseEntity<ReponseDTO> sendverify(@RequestParam("Email") String Email) {
	    ReponseDTO statusReponse = new ReponseDTO();
	    System.out.println("check input" + Email);
	    Random random = new Random();
        int min = 100000;
        int max = 999999;
        int verificationCode = random.nextInt(max - min + 1) + min;
//	    User user = userservice.findByEmail(Email);
        User AccountForgotpass = userservice.findByEmail(Email);
        System.out.println("check account" + AccountForgotpass);
        if(AccountForgotpass != null) {
        	 MailInfo mailSend = new MailInfo();
     	    mailSend.setFrom("artdevk18@gmail.com");
             mailSend.setTo(Email);
             mailSend.setSubject("Verification Code for Password Change");
             mailSend.setBody("Your verification code is: " + verificationCode);
             
             VerificationDTO data  = new VerificationDTO();
             data.setEmail(Email);
             data.setVerificationCode(verificationCode);
             
     	    try {
     			mailservice.send(mailSend);
     			statusReponse.setMessage("Gửi mã xác nhận thành công!");
     			statusReponse.setModel(data);
     			statusReponse.setStatusCode(200);
     		} catch (MessagingException e) {
     			// TODO Auto-generated catch block
     			System.out.println(e);
     			statusReponse.setMessage("Gửi mã xác nhận Không thành công!");

     			statusReponse.setStatusCode(400);
     			e.printStackTrace();
     		}
        }else {
        	statusReponse.setMessage("Email Không Tồn Tại !");

 			statusReponse.setStatusCode(403);
        }
	   
	   
	    return ResponseEntity.ok(statusReponse);
	}
	
	@PostMapping(value = "/api/forgotpassword")
	public ResponseEntity<ErrorResponseDTO> Forgotpassword(@RequestParam("Email") String Email,@RequestParam("Newpassword") String Newpassword) {

	    System.out.println("check input" + Email + "new Password" + Newpassword);
	    
	    User user = userservice.findByEmail(Email);
	    ErrorResponseDTO statusReponsedto = new ErrorResponseDTO();
	   if(user != null) {
		   user.setPassword(new BCryptPasswordEncoder().encode(Newpassword));
	    	userservice.saveUser(user);
	    	statusReponsedto.setErrorCode(200);
	    	statusReponsedto.setMessage("Cập Nhật Mật Khẩu Mới Thành Công!");
	   }else {
		   statusReponsedto.setErrorCode(400);
	    	statusReponsedto.setMessage("Cập Nhật Mật Khẩu Mới Thất Bại!");
	   }

	    return ResponseEntity.ok(statusReponsedto);
	}

}
