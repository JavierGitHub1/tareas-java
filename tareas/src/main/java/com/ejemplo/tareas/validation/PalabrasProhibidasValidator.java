package com.ejemplo.tareas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PalabrasProhibidasValidator implements ConstraintValidator<NoPalabrasProhibidas, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(value == null) return  true;

        return !value.toLowerCase().contains("borrar") && !value.toLowerCase().contains("estupido");
    }
}
