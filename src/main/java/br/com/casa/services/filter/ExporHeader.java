package br.com.casa.services.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


/***
 * 
 *  Criado apenas para expor o header location
 *  é automaticamente adicionado como componente @Component
 * */
@Component
public class ExporHeader implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	    
		      HttpServletResponse res = (HttpServletResponse) response;
		       res.addHeader("access-control-expose-headers" , "location" );
		          chain.doFilter(request, response);
		
	}

}
