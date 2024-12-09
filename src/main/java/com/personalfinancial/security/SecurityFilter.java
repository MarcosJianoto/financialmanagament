package com.personalfinancial.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.personalfinancial.repositories.UsersFinancialRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	UsersFinancialRepository usersFinancialRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
			filterChain.doFilter(request, response);
			return;
		}

		var token = this.recoverToken(request);
		if (token != null) {
			var email = tokenService.validateToken(token);

			if (email != null) {
				var user = usersFinancialRepository.findByEmail(email);

				if (user != null) {
					var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return null;
		}
		return authHeader.replace("Bearer ", "").trim();
	}

}
