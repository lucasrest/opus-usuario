package br.com.opus.auth.anotation;

import br.com.opus.auth.anotation.validator.PasswordConstraintValidator;
import br.com.opus.auth.model.constants.EXCEPTION;
import br.com.opus.auth.anotation.validator.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default EXCEPTION.SENHA_INVALIDA;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
