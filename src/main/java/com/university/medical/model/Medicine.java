package com.university.medical.model;

import java.util.Objects;

/**
 * Лекарственный препарат.
 *
 * @param name       торговое название лекарства
 * @param ingredient международное непатентованное название (МНН) активного вещества
 */

public record Medicine (

    String name,
    String ingredient
) {
    /**
     * Компактный конструктор с валидацией.
     *
     * @throws NullPointerException     если любое поле null
     * @throws IllegalArgumentException если name или ingredient пустые
     */
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
