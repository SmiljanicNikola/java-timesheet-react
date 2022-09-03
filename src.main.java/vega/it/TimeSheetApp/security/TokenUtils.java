package vega.it.TimeSheetApp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {
	
	 @Value("timesheet")
	 private String secret;

	 @Value("1800")
	 private Long expiration;
	 
	 
	 public String getUsernameFromToken(String token) {
	        try {
	            Claims claims = this.getClaimsFromToken(token);
	           return claims.getSubject();
	        } catch (Exception e) {
	            return null;
	        }
	    }
	 
	 
	 private Claims getClaimsFromToken(String token) {
	        try {
	        	Claims claims = Jwts.parser().setSigningKey(this.secret)
	                    .parseClaimsJws(token).getBody();
	            return claims;
	        } catch (Exception e) {
	            return null;
	        }
	    }
	 
	 
	 public Date getExpirationDateFromToken(String token) {
	        try {
	            final Claims claims = this.getClaimsFromToken(token);
	            Date expirationDate = claims.getExpiration();
	            return expirationDate;
	        } catch (Exception e) {
	            return null;
	        }
	        
	    }
	 
	 
	 private boolean isTokenExpired(String token) {
	        final Date expirationDate = this.getExpirationDateFromToken(token);
	        return expirationDate.before(new Date(System.currentTimeMillis()));
	    }
	 
	 
	 public boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return username.equals(userDetails.getUsername())
	                && !isTokenExpired(token);
	    }
	
	 
	 public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<String, Object>();
	        claims.put("sub", userDetails.getUsername());
	        claims.put("role", userDetails.getAuthorities().toArray()[0]);
	        claims.put("created", new Date(System.currentTimeMillis()));
	        return Jwts.builder().setClaims(claims)
	                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
	                .signWith(SignatureAlgorithm.HS512, secret).compact();
	    }
}
