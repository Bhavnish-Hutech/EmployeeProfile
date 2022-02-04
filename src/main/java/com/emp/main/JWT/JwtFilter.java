package com.emp.main.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.emp.main.service.EmployeeService;

@Component
public class JwtFilter extends OncePerRequestFilter{
	

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private jwthelper helper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String token = null;
		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer "))
		{
			token = requestTokenHeader.substring(7);
			username = helper.extractUsername(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails =this.service.loadUserByUsername(username);
			if(helper.validateToken(token, userDetails))
			{

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken .setDetails( new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		
		filterChain.doFilter(request, response);
		
	}

}
