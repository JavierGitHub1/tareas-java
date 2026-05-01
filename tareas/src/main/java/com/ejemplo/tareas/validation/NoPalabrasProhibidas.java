package com.ejemplo.tareas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PalabrasProhibidasValidator.class)
public @interface NoPalabrasProhibidas {
    String message() default "La descripción tiene palabras no permitidas";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
