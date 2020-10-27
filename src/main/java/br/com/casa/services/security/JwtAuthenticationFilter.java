package br.com.casa.services.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casa.dominio.DTO.CredenciaisDTO;

/**
 * 
 * Classe totalmente boileplate , copiar e colar e apenas adequar
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;
	private JWTUtil jwtUtil;
	
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


	/**
	 * injeção das propriedades pelo construtor
	 * 
	 */
	public JwtAuthenticationFilter(AuthenticationManager authManager, JWTUtil jwtUtil) {
		super();
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * Tentativa de autenticação
	 * quando for solicitado algo para /login Recupera o request e converte para
	 * otipo de credenciais
	 * 
	 * em seguida faz a instanciação de passwordAuthToken do spring security, 
	 **/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		log.info("Realizando tentativa de login...");

		CredenciaisDTO creds = null;
		try {
			creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(creds.getEmail(),
				creds.getSenha(), new ArrayList<>());

		Authentication authenticate = authManager.authenticate(auth);
		log.info("Credenciais recuperadas " + creds.getEmail() + " - Acesso do tipo"
				+ authenticate.getAuthorities().toString());
		
		return authenticate;

	}

	/**
	 * 
	 * Gera um token e coloca na resposta da requisição, recebe o auth que foi gerado no attempt
	 * 
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		   String usuario = 	((DetalhesDeUsuario) authResult.getPrincipal()).getUsername();
		   String token  = jwtUtil.generateToken(usuario);
		   response.addHeader("Authorization" , "Bearer " + token);
	}
}
