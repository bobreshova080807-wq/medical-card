package com.university.medical.model;

import java.util.Objects;

public record Medicine (

    String name,
    String ingredient
){
    public Medicine {
        Objects.requireNonNull(name, "Название препарата не может быть пустым.");
        Objects.requireNonNull(ingredient, "Активное вещество не может быть пустым.");

        if(name.isBlank()){
            throw new IllegalArgumentException("Название препарата не может быть пустым.");
        }

        if(ingredient.isBlank()){
            throw new IllegalArgumentException("Активное вещество не может быть пустым.");
        }

    }
}
