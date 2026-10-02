package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public record Patient(
    String name,
    String policy,
    LocalDate birthday
){
    public Patient {

        Objects.requireNonNull(name, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(policy, "Номер полиса не может быть пустым.");
        Objects.requireNonNull(birthday, "Дата рождения не может быть пустой.");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя пациента не может быть пустым");
        }
        if (policy.isBlank()) {
            throw new IllegalArgumentException("Номер полиса не может быть пустым");
        }

    }

}