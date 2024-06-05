package com.foodorder.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

	public String generateTocken(Authentication auth) {
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String role = populateAuthorities(authorities);

		String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration((new Date(new Date().getTime() * 86400000)))
				.claim("email", auth.getName())
				.claim("authorities", role)
				.signWith(key)
				.compact();

		return jwt;
	}

	private String getEmailFromJwtTocken(String jwt) {
		jwt = jwt.substring(7);
		
	}
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> auths = new HashSet<>();

		for (GrantedAuthority authority : authorities) {
			authorities.add(authority.getAuthority());
		}

		return String.join(",", auths);
	}
}
