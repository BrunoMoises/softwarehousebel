package br.edu.iftm.upt.softwarehouseBeL2.validation.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.iftm.upt.softwarehouseBeL2.validation.NomeUsuarioValido;
//pelo menos uma letra
//pelo menos um digito
//pelo menos 1 caractere especial
//tamanho mínimo 6
//tamanho máximo 20
//pelo menos 1 caractere maiúsculo
//pelo menos 1 caractere minúsculo
//não pode ser um nome de usuário proibido
public class NomeUsuarioValidoValidator implements ConstraintValidator<NomeUsuarioValido, String> {

	private static final Logger logger = LoggerFactory.getLogger(NomeUsuarioValidoValidator.class);
	
	private static final List<String> LISTA_CARACTERES_ESPECIAIS = Arrays.asList("!", "@", "#", "$", "%", "&", "*", ".", ",", "~", "^", "/",
		"\\", "+", ":", ";", "=", "'", "`", "[", "]", "(", ")", "{", "}", "<", ">", "-", "_");
	private static final List<String> LISTA_NOMES_USUARIO_PROIBIDOS = Arrays.asList("root", "eu", "admin");

	@Override
	public boolean isValid(String nomeUsuario, ConstraintValidatorContext constraintValidatorContext) {
		if (nomeUsuario != null) {
			return peloMenosUmaLetra(nomeUsuario) &&
				   peloMenosUmDigito(nomeUsuario) &&
				   peloMenosUmCaractereEspecial(nomeUsuario) &&
				   tamanhoMinimo(nomeUsuario) &&
				   tamanhoMaximo(nomeUsuario) &&
				   peloMenosUmCaractereMaiusculo(nomeUsuario) &&
				   peloMenosUmCaractereMinusculo(nomeUsuario) &&
				   naoEhProibido(nomeUsuario);
		} else {
			return true;
		}
	}

	private boolean peloMenosUmaLetra(String nomeUsuario) {
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (Character.isAlphabetic(nomeUsuario.charAt(pos))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean peloMenosUmDigito(String nomeUsuario) {
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (Character.isDigit(nomeUsuario.charAt(pos))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean peloMenosUmCaractereEspecial(String nomeUsuario) {
		logger.debug("Recebeu o nomeUsuario: {}", nomeUsuario);
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (LISTA_CARACTERES_ESPECIAIS.contains(String.valueOf(nomeUsuario.charAt(pos)))) {
				logger.debug("O nomeUsuario: {} tem um caractere especial na posição: {}", nomeUsuario, pos);
				return true;
			}
		}
		return false;
	}
	
	private boolean tamanhoMinimo(String nomeUsuario) {
		return nomeUsuario.length() > 5;
	}

	private boolean tamanhoMaximo(String nomeUsuario) {
		return nomeUsuario.length() < 21;
	}

	private boolean peloMenosUmCaractereMaiusculo(String nomeUsuario) {
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (Character.isUpperCase(nomeUsuario.charAt(pos))) {
				return true;
			}
		}
		return false;
	}

	private boolean peloMenosUmCaractereMinusculo(String nomeUsuario) {
		for (int pos = 0; pos < nomeUsuario.length(); pos++) {
			if (Character.isLowerCase(nomeUsuario.charAt(pos))) {
				return true;
			}
		}
		return false;
	}

	private boolean naoEhProibido(String nomeUsuario) {
		return !LISTA_NOMES_USUARIO_PROIBIDOS.contains(nomeUsuario.toLowerCase());
	}
}
