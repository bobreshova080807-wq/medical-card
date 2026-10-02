package com.university.medical.model;

import java.util.Objects;

public record Doctor (
    String name,
    String id,
    Specialty specialty
){
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
