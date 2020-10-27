package br.com.casa.services.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * é um componente que vai ser responsável pela geração do token apartir de um secret em prperties
 **/
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);

	
	/***
	 *  Realiza a geração do token JWT
	 * */
	public String generateToken(String username) {
		log.debug("Obtendo token de usuário para o usuário [" + username + "]");
		return Jwts
				  .builder()
				  .setSubject(username)
		          .setExpiration(new Date(System.currentTimeMillis() + expiration))
		          .signWith(SignatureAlgorithm.HS512	, secret.getBytes())
		          .compact();
	}
}
