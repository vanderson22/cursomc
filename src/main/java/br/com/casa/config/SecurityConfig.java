
package br.com.casa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.casa.services.security.JWTAuthorizationFilter;
import br.com.casa.services.security.JWTUtil;
import br.com.casa.services.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // pre autoriza certos perfis  EXEMPLO : @PreAuthorize("hasAnyrole('ADMIN')") NOS resources
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// para liberar o h2
	@SuppressWarnings("unused")
	@Autowired
	private Environment env;
	
	// vai reconhecer automaticamente a unica classe que implementa essa interface
	// DetalhesUsuarioServiceImpl
	@Autowired 
	private UserDetailsService userDetail;

	@Autowired
	private JWTUtil jwtUtil;

	/**
	 * urls liberadas
	 * 
	 **/
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**", "/h2/**", };

	private static final String[] PUBLIC_MATCHERS_GET = { "/categorias/**", "/produtos/**"};
	private static final String[] PUBLIC_MATCHERS_POST = { "/clientes/**" ,  "/clientes/upload" };


	/**
	 *  Esse cara vai ser capaz de buscar o usuário pelo e-mail usando o userDetail informado
	 * @throws Exception 
	 * 
	 * ***/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth
		   .userDetailsService(userDetail)
		   .passwordEncoder(criarBCrypt());
	}

	/**
	 * 
	 * Vai permitir requests para as url's de PUBLIC MATCHERS
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Starting webSecurity configure");
		
		
		// Liberar os frames do h2, mesmo com as urls liberadas os frames são
		// bloqueados.
		http.headers().frameOptions().disable();

		http.cors()
		     .and()
		     .csrf()
		     .disable()
		     .authorizeRequests()
		     .antMatchers(PUBLIC_MATCHERS).permitAll()
			 .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			 .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			 .anyRequest().authenticated();
		http .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil )) ;// registrar o filtro de authenticação
        http .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetail)); // registra o filtro de authorização
				// this disables session creation on Spring Security
        http .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	/**
	 * Habilitar crossOrigin Poderia usar @CrossOrigin nos resources ? -- CTRL C
	 * CTRL V
	 **/
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	/**
	 * Cria um componente encoder na memoria para autowired
	 * 
	 **/
	@Bean
	public BCryptPasswordEncoder criarBCrypt() {

		return new BCryptPasswordEncoder();
	}
}
