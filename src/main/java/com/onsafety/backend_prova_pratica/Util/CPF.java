package com.onsafety.backend_prova_pratica.Util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Anotação customizada para validar CPF.
 */
@Documented
@Constraint(validatedBy = CPFValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CPF {
    String message() default "CPF inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}