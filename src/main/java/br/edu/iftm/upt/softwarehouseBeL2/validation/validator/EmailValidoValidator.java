package br.edu.iftm.upt.softwarehouseBeL2.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.iftm.upt.softwarehouseBeL2.repository.UsuarioRepository;
import br.edu.iftm.upt.softwarehouseBeL2.validation.Emailunico;


public class EmailValidoValidator implements ConstraintValidator<Emailunico, String> {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUnicoValidator.class);
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null) {
			long quantos = repository.countByEmailUsuarioContainingIgnoreCase(value);
			logger.debug("Email {} aparece no BD {} vezes", value, quantos);
			return quantos == 0;
		} else {
			return true;
		}
	}

}
