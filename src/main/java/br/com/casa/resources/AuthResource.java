package br.com.casa.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.services.ProdutoService;
import br.com.casa.services.UsuarioService;
import br.com.casa.services.security.DetalhesDeUsuario;
import br.com.casa.services.security.JWTUtil;

/**
 * 
 ***/

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	@Autowired
	private JWTUtil jwt;

	/**
	 * 
	 *
	 */
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
	
		DetalhesDeUsuario usuario = UsuarioService.autenticado();
		String token = jwt.generateToken(usuario.getUsername());
		
		response.addHeader("Authorization", "Bearer " + token);

		return ResponseEntity.noContent().build();
	}

}