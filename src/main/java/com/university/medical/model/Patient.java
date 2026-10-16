package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Пациент медицинской карты.
 * Идентифицируется номером полиса ОМС.
 *
 * @param name      полное имя пациента
 * @param policy    номер полиса ОМС
 * @param birthday  дата рождения
 */

public record Patient(
    String name,
    String policy,
    LocalDate birthday
) {
    /**
     * Компактный конструктор с валидацией полей.
     * Проверяет, что все поля не null и не пустые.
     *
     * @throws NullPointerException     если любое поле null
     * @throws IllegalArgumentException если name или policy пустые
     */
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