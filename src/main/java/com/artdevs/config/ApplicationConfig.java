package com.artdevs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	private final UserRepository userrep;
	
	@Bean
    UserDetailsService userDetailsService() {
        return username -> userrep.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
	
//	@Bean
//	UserDetailsService userDetailsService() {
//	    return username -> {
//	        User user = userrep.findByEmail(username).orElse(null);
//	        if (user == null) {
//	            throw new UsernameNotFoundException("User not found");
//	        }
//	        if (user.isAccountNonLocked()) {
//	            throw new UsernameNotFoundException("User account is locked");
//	        }
//	        return user;
//	    };
//	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
