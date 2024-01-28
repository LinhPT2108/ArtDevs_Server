package com.artdevs.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.artdevs.config.auth.AuthenticationRequest;
import com.artdevs.config.auth.AuthenticationResponse;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.UserDTO;
import com.artdevs.mapper.UserMapper;
import com.artdevs.repositories.user.RoleRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	@Autowired
	private final UserRepository userrep;
	
	
	@Autowired UserService userservice;
	
	@Autowired
	private RoleRepository rolerep;

	@Autowired
	JwtTokenProvider jwtService;
	@Autowired
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		// System.out.println(authenticationRequest.getEmail());
		User user = userrep.findByEmail(authenticationRequest.getEmail()).get();
		// System.out.println(user.getEmail());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
				authenticationRequest.getPassword()));

		UserDTO userdto = UserMapper.UserConvertToUserDTO(user);
		
		Role role = null;
		if (user != null) {
			role = rolerep.findByUserRole(user);
			System.out.println("role"+ role.getRoleName());
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		String jwtToken = jwtService.generateToken(user, authorities);
		String jwtRefeshToken = jwtService.generateRefeshToken(user, authorities);
		user.setIsOnline(true);
		userservice.updateUser(user);
		return AuthenticationResponse.builder().token(jwtToken).refeshToken(jwtRefeshToken).userdto(userdto).build();
	}
	
	// public AuthenticationResponse authenticate(AuthenticationRequest
	// authenticationRequest) {
	// authenticationManager.authenticate(new
	// UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
	// authenticationRequest.getPassword() ));
	// User user =
	// userrep.findByEmail(authenticationRequest.getEmail()).orElseThrow();
	// Role role = null;
	// if(user!=null) {
	// role = rolerep.findByUserRole(user);
	// }
	//
	// Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
	// List<AccountRole> sets = new ArrayList<>();
	// accountRoles.stream().forEach(accountRole -> sets.add(new
	// AccountRole(accountRole.getUser(), accountRole.getRole())));
	// user.setUserRole(sets);
	// sets.stream().forEach(set -> authorities.add(new
	// SimpleGrantedAuthority(set.getRole().getRoleName())));
	// var jwtToken = jwtService.generateToken(user, authorities);
	// var jwtRefeshToken = jwtService.generateRefeshToken(user, authorities);
	// return AuthenticationResponse.builder()
	// .token(jwtToken)
	// .refeshToken(jwtRefeshToken)
	// .accoutnDTO(AccountMapper.convertToDto(user, promotionalDetailsDAO,
	// flashSaleDAO, productDAO))
	// .build();
	// }

}
