package com.application.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.application.exception.ClaimsIsNullException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtService {
	ObjectMapper mapper = new ObjectMapper();

	public String getUsernameFromJwtToken(String token) {
		Claims claims = extractAllClaims(token);
		if (claims == null) {
			throw new ClaimsIsNullException("JWT có lỗi");
		}
		System.err.println(claims);
		return claims.getSubject();
	}

	public Date getExpiryDate(String token) {
		Claims claims = extractAllClaims(token);
		return claims.getExpiration();
	}

	public Claims extractAllClaims(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token).getBody();

		} catch (MalformedJwtException ex) {
//      log.error("Invalid JWT token");
		} catch (SignatureException ex) {
//	      log.error("Unsupported signature algorithm");
		} catch (ExpiredJwtException ex) {
//      log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//      log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//      log.error("JWT claims string is empty.");
		}
		return claims;
	}

	public boolean isValidToken(String token, UserDetails userDetails) {
		String username = getUsernameFromJwtToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(SecurityConstant.JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
		}
		return false;
	}

	// new code
	public boolean validateToken2(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJwt(authToken);
			return true;
		} catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
		}
		return false;
	}

	private boolean isTokenExpired(String token) {
		return getExpiryDate(token).before(new Date());
	}

	public String genarateToken(String username) {
//		String scope = authentication.getAuthorities().stream()
//				.map(GrantedAuthority::getAuthority)
//				.collect(Collectors.joining(" "));
//		String scope = "USER";
//		if (username.equals("teonv")) {
//			System.err.println("admin");
//			scope += ",ADMIN";
//		}
		Date current = new Date();
		Date expiryDate = new Date(current.getTime() + SecurityConstant.JWT_EXPIRATION);
//		https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string
		return Jwts.builder().setSubject(username).setIssuedAt(current).setExpiration(expiryDate)
//				.claim("scope", scope)
				.signWith(getSignInkey(), SignatureAlgorithm.HS512).compact();
//		return Jwts.builder().setSubject(username).setIssuedAt(current).setExpiration(expiryDate).signWith(SignatureAlgorithm.ES512, SecurityConstant.JWT_SECRET).compact();
	}

	private Key getSignInkey() {
		byte[] data = Decoders.BASE64.decode(SecurityConstant.JWT_SECRET);
		return Keys.hmacShaKeyFor(data);
	}
}
