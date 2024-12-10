package com.personalfinancial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize

						// register and login user
						.requestMatchers(HttpMethod.POST, "/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/register").permitAll()
						.requestMatchers(HttpMethod.POST, "/expenses").permitAll()

						// Permissions for revenues
						.requestMatchers(HttpMethod.POST, "/saverevenues").hasRole("ADMIN")
						.requestMatchers(HttpMethod.GET, "/getrevenues/{id}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/updaterevenues/{id}").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/deleterevenues{id}").hasRole("ADMIN")

						// Permissions for expenses
						.requestMatchers(HttpMethod.POST, "/saveexpenses").permitAll()
						.requestMatchers(HttpMethod.GET, "/getexpenses/{id}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/updateexpenses/{id}").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/deleteexpenses/{id}").permitAll()

						// Permissions for category
						.requestMatchers(HttpMethod.POST, "/financialcategory").permitAll()
						.requestMatchers(HttpMethod.GET, "/financialcategory/{id}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/financialcategory/{id}").permitAll()
						.requestMatchers(HttpMethod.PUT, "/editcategorycolor/{id}").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/financialcategory/{id}").permitAll()

						.anyRequest().authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
