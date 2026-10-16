package com.university.medical.model;

import java.util.Objects;

/**
 * Врач медицинской карты.
 * Имеет идентификатор, полное имя и специальность.
 *
 * @param id        уникальный идентификатор врача
 * @param name      полное имя врача
 * @param specialty специальность врача
 */

public record Doctor (
    String name,
    String id,
    Specialty specialty
) {
    /**
     * Компактный конструктор с валидацией полей.
     *
     * @throws NullPointerException     если любое поле null
     * @throws IllegalArgumentException если id или fullName пустые
     */
    public Doctor{
        Objects.requireNonNull(name, "Имя врача не может быть пустым.");
        Objects.requireNonNull(id, "ID врача не может быть пустым.");
        Objects.requireNonNull(specialty, "Специальность врача не может быть пустой.");

        if (name.isBlank()){
            throw new IllegalArgumentException("Имя врача не может быть пустым.");
        }

        if (id.isBlank()){
            throw new IllegalArgumentException("ID врача не может быть пустым.");
        }

    }

}