package br.com.casa.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.DTO.ClienteDTO;
import br.com.casa.dominio.annotations.validation.ClienteInsert;
import br.com.casa.dominio.annotations.validation.ClienteUpdate;
import br.com.casa.exceptions.FieldMessage;
import br.com.casa.repositories.ClienteRepository;

/**
 * ClienteInsert é o tipo da anotação ClienteNEWDTO é o tipo que vai receber a
 * anotação.
 * 
 **/
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> { // atenção na troca do
																								// dto

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private HttpServletRequest request;

	public void initialize(ClienteInsert ann) {
		System.out.println("Iniciada validação do DTO ");
	}

	/**
	 * Retorna true se for valido.
	 * 
	 * O objeto fieldMessage vai conter os erros de validação.
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// Capiturar o Id da url para validar com @Valid logo na entrada.
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));

		Cliente cl = repo.findByEmail(objDTO.getEmail());
		if (cl != null && !cl.getId().equals(id))
			list.add(new FieldMessage("email", "Email já existe para outro cliente."));

		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation(); // está desabilitando o default e na proxima linha habilitando
			context.buildConstraintViolationWithTemplate(f.getMensagem()).addPropertyNode(f.getCampo())
					.addConstraintViolation();

		}
		return list.isEmpty();
	}

}
