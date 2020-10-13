package br.com.casa.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.casa.dominio.ClienteNewDTO;
import br.com.casa.dominio.annotations.validation.ClienteInsert;
import br.com.casa.dominio.enums.TipoCliente;
import br.com.casa.exceptions.FieldMessage;
import br.com.casa.services.util.BR;

/**
 * ClienteInsert é o tipo da anotação ClienteNEWDTO é o tipo que vai receber a
 * anotação.
 * 
 **/
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	public void initialize(ClienteInsert ann) {
		System.out.println("Iniciada validação do DTO ");
	}

	/**
	 * Retorna true se for valido.
	 * 
	 * O objeto fieldMessage vai conter os erros de validação.
	 **/
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDTO.getNome() == null) {

			list.add(new FieldMessage("nome", "Nome não pode ser nulo"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && BR.isValidCPF(objDTO.getCpfCnpj())) {

			list.add(new FieldMessage("cpfCnpj", "CPF inválido - Informar com máscara 000.000.000-00"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && BR.isValidCNPJ(objDTO.getCpfCnpj())) {

			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}
		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation(); // está desabilitando o default e na proxima linha habilitando
			context.buildConstraintViolationWithTemplate(f.getMsg()).addPropertyNode(f.getFieldName())
					.addConstraintViolation();

		}
		return list.isEmpty();
	}

}
