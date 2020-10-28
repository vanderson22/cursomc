package br.com.casa.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.casa.services.security.DetalhesDeUsuario;

public class UsuarioService {

	
	/***
	 *  Chama o spring security para recuperar qual usuário logado
	 *  
	 * */
	public static DetalhesDeUsuario autenticado() {
		try {
			return  (DetalhesDeUsuario) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
