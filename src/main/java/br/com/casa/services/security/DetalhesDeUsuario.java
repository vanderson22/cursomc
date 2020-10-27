package br.com.casa.services.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.casa.dominio.enums.Perfil;


/**
 * 
 *  Realiza o mapeamento de detalhes do usuário e suas caracteristicas
 *  conta bloqueada e etc.
 * **/
public class DetalhesDeUsuario implements UserDetails {

	private Integer id;
	private String email;
	private String senha;

	private Collection<? extends GrantedAuthority> authorities;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DetalhesDeUsuario(Integer id, String senha, String email, Set<Perfil> perfis) {
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getEstado()))
				.collect(Collectors.toList());
		this.email = email;
		this.id = id;
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {

		return senha;
	}

	public Integer getId() {

		return id;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Lógica de negócio para conta expirar true
		return true;
	}

}
