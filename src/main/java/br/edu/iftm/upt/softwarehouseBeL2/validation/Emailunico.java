package br.edu.iftm.upt.softwarehouseBeL2.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.edu.iftm.upt.softwarehouseBeL2.validation.validator.EmailUnicoValidator;
@Constraint(validatedBy = EmailUnicoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Emailunico {

	public String message() default "Já existe um contato com esse e-mail";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
    
}
