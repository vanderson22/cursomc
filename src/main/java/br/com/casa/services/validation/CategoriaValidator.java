package br.com.casa.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.DTO.CategoriaDTO;
import br.com.casa.dominio.annotations.validation.CategoriaInsert;
import br.com.casa.exceptions.FieldMessage;
import br.com.casa.services.CategoriaService;

public class CategoriaValidator implements ConstraintValidator<CategoriaInsert, CategoriaDTO> {

	@Autowired
	private CategoriaService catService;

	@Override
	public boolean isValid(CategoriaDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();

		for (Categoria categoria : catService.buscarTodos()) {

			if (categoria.getNome().equalsIgnoreCase(value.getNome()))
				list.add(new FieldMessage("nome", "Nome da categoria já existe"));
		}

		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation(); // está desabilitando o default e na proxima linha habilitando
			context.buildConstraintViolationWithTemplate(f.getMensagem()).addPropertyNode(f.getCampo())
					.addConstraintViolation();

		}

		return list.isEmpty();
	}

}
