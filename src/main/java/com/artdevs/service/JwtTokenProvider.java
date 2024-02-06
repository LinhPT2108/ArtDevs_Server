package com.artdevs.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.artdevs.domain.entities.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	
	private static final String secret_key = "123";

	public String generateToken(User user, Collection<SimpleGrantedAuthority> authorities) {
		Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 86400*1000))
				.withClaim("role",user.getRole().getRoleName())
				.sign(algorithm);
	}

	public String generateRefeshToken(User user, Collection<SimpleGrantedAuthority> authorities) {
		Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
		return JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 45 * 86400*1000)).sign(algorithm);
	}

	public boolean validateToken(String authToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject("art-backend")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(authToken);

            Date expirationDate = decodedJWT.getExpiresAt();
            Instant expirationInstant = expirationDate.toInstant();
            LocalDate expirationLocalDate = LocalDate.ofInstant(expirationInstant, ZoneId.systemDefault());
            if (expirationLocalDate.isBefore(LocalDate.now())) {
                System.out.println("Expired JWT token");
                return false;
            }

            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT token signature");
        } catch (JWTVerificationException ex) {
            System.out.println("Invalid JWT token");
        }
        return false;
    }
	 public void deleteToken(String authToken) {
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(secret_key.getBytes());
	            JWTVerifier verifier = JWT.require(algorithm)
	                    .build();
	            DecodedJWT decodedJWT = verifier.verify(authToken);

	            // Đặt thời gian hết hạn ngay lập tức để token trở thành không hợp lệ
	            Algorithm invalidateAlgorithm = Algorithm.HMAC256("invalid".getBytes());
	            String invalidatedToken = JWT.create()
	                    .withSubject(decodedJWT.getSubject())
	                    .withExpiresAt(new Date())
	                    .sign(invalidateAlgorithm);

	            // In ra để kiểm tra, bạn có thể thay thế bằng cách khác như lưu vào một danh sách token đã hết hạn
	            System.out.println("Invalidated Token: " + invalidatedToken);
	        } catch (SignatureException ex) {
	            System.out.println("Invalid JWT token signature");
	        } catch (JWTVerificationException ex) {
	            System.out.println("Invalid JWT token");
	            System.out.println(ex);
	        }
	    }
    // Phương thức để xóa token
   

}
