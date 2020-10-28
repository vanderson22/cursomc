package br.com.casa.services.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * é um componente que vai ser responsável pela geração do token apartir de um
 * secret em prperties
 **/
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

	/***
	 * Realiza a geração do token JWT
	 */
	public String generateToken(String username) {
		log.debug("Obtendo token de usuário para o usuário [" + username + "]");
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	/**
	 * Verifica se um token é valido com o objeto Claims = Reivindicações Se as
	 * reivindicações forem nulas retorna falso e se qualquer um dos claims falhar
	 * retorna falso
	 */
	public boolean isTokenvalido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {

			String username = claims.getSubject();
			Date expirationToken = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());

			if (username != null && expirationToken != null && now.before(expirationToken)) {
               log.info("Token é valido  Expiration " + expirationToken);
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Obtem uma reinvindição a partir de um token
	 */
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	/**
	 * Retorna um usuário do jwt ou nulo
	 * 
	 */
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();

		}

		return null;
	}
 
}
