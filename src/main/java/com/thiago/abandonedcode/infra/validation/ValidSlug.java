package com.thiago.abandonedcode.infra.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidSlug {
    String message() default "Slug inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
