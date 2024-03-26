package com.artdevs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.artdevs.config.auth.JwtAuthenticationFilter;
import com.artdevs.utils.Global;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiger {
		private final AuthenticationProvider authenticationProvider;

		private final JwtAuthenticationFilter jwtAuthenticationFilter;

		/*
		 * config đường dẫn và giao thức đăng nhập, đăng xuất, path_api = "/rest"
		 * path_api = "/admin" đăng nhập với Email và Password Pass word : 123123a ->
		 * đã được mã hóa thành
		 * "$2a$10$kpnU5NRvBiGYfLoH.GuQ5uUFHx6M37QuihnsfN1z60VqCzX24HFZK" Cập nhật
		 * password đã được mã hóa vào csdl
		 */
		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers(AntPathRequestMatcher.antMatcher("/*"),
							AntPathRequestMatcher.antMatcher("/account/login"),
							AntPathRequestMatcher.antMatcher("/product"), AntPathRequestMatcher.antMatcher("/product/**"),
							AntPathRequestMatcher.antMatcher("/api/login"),
							AntPathRequestMatcher.antMatcher("/api/**"))
					.permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher(Global.path_api + "/cart"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/cart/**"))
					.hasAnyAuthority("user")
					.requestMatchers(AntPathRequestMatcher.antMatcher("/account/**"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/account"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/account/**"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/account"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/account/**"))
					.hasAnyAuthority("admin", "staff", "shipper", "user")
					.requestMatchers(AntPathRequestMatcher.antMatcher(Global.path_api + "/order"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/order/**"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/order"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/order/**"))
					.hasAnyAuthority("admin", "staff", "shipper")
					.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/dashboard"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/product"),
							AntPathRequestMatcher.antMatcher(Global.path_api + "/product/**"),
	AntPathRequestMatcher.antMatcher(Global.path_api + "/flashSale"))
					.hasAnyAuthority("admin", "staff")
					.requestMatchers(AntPathRequestMatcher.antMatcher(Global.path_api + "/userCustom"))
					.hasAnyAuthority("admin")
					.anyRequest().permitAll())

		        .formLogin(login -> login
		                .loginPage("/login")
		                .defaultSuccessUrl("/", false)
		                .permitAll()
		                )
//					.formLogin(login -> login.loginPage("/account/login").loginProcessingUrl("/api/login")
//	.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/login/success", true))
					.logout(logout -> logout.logoutUrl("/account/logout").logoutSuccessUrl("/account/login")
							.invalidateHttpSession(true).deleteCookies("JSESSIONID"))
					.authenticationProvider(authenticationProvider)
					.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			return http.build();
		}

		/*
		 * Config cho phép truy cập vào tất cả các file trong pattern của antMatcher
		 */
		@Bean
		WebSecurityCustomizer webSecurityCustomizer() {
			return web -> web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher("/static/**"),
					AntPathRequestMatcher.antMatcher("/templates/**"));
		}
	}
