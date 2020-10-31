package br.com.casa.services.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/***
 * 
 * Realiza um filtro de autorização
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailService;
	private static final Logger log = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService uds) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailService = uds;
	}

	/**
	 * Ao realizar o filtro verifica se o header authorization é valido
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		log.info("Recuperando header da requisição [" + header + "]");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, header);
			// liberar autorização
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
		}

		chain.doFilter(request, response);

	}

	/**
	 * 
	 * Recupera um token se ele for válido
	 **/
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req, String header) {
		String token = header.substring(7);
		if (jwtUtil.isTokenvalido(token)) {
			// pega usuário do token
			String username = jwtUtil.getUsername(token);
			// consulta no banco de dados
			UserDetails userByUsername = userDetailService.loadUserByUsername(username);
			log.info("Autorização concedida para o usuário : " + userByUsername.getUsername());

			// importante usar esse construtor com 3 argumentos pois ele informa .setAuthenticated(true) !!!
			return new UsernamePasswordAuthenticationToken(userByUsername, null,
					userByUsername.getAuthorities());
		}
		return null;
	}

}
