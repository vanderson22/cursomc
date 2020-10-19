package br.com.casa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// para liberar o h2
	@SuppressWarnings("unused")
	@Autowired
	private Environment env;
	
	/**
	 * urls liberadas
	 * 
	 **/
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**", 
			"/h2/**", 
			};
	
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/categorias/**" ,
			"/produtos/**" 
			};

	/**
	 * 
	 * Vai permitir requests para as url's de PUBLIC MATCHERS
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
       System.out.println("Starting webSecurity configure");
		// Liberar os frames do  h2, mesmo com as urls liberadas os frames são bloqueados. 
 		  http.headers().frameOptions().disable();
		 
		 http
		 .cors().and().csrf().disable()
		 .authorizeRequests()
         	.antMatchers(PUBLIC_MATCHERS).permitAll()
         	.antMatchers( HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
        	.anyRequest().authenticated()
         .and()
//         .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//         .addFilter(new JWTAuthorizationFilter(authenticationManager()))
         // this disables session creation on Spring Security
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

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
	 *   Cria um componente encoder na memoria para autowired
	 * 
	 * **/
	@Bean
	public BCryptPasswordEncoder criarBCrypt() {
		
		return new BCryptPasswordEncoder();
	}
}
