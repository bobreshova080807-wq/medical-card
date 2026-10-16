package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Запись о диагнозе в медицинской карте.
 *
 * @param date        дата постановки диагноза
 * @param diagnosis   код диагноза (например, J06.9)
 * @param description текстовое описание диагноза
 */

public record DiagnosisRecord (
    LocalDate date,
    String diagnosis,
    String description

) implements MedicalRecord {

    /**
     * Компактный конструктор с валидацией.
     *
     * @throws NullPointerException     если любое поле null
     * @throws IllegalArgumentException если diagnosisCode или descriptionText пустые
     */
    public DiagnosisRecord {
        Objects.requireNonNull(date, "Дата предписания не может быть пустой.");
        Objects.requireNonNull(diagnosis, "Диагноз не может быть пустым.");
        Objects.requireNonNull(description, "Описание диагноза не может быть пустым.");

        if(diagnosis.isBlank()){
            throw new IllegalArgumentException("Диагноз не может быть пустым.");
        }

        if(diagnosis.isBlank()){
            throw new IllegalArgumentException("Описание диагноза не может быть пустым.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDate getDate() {
        return date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format("Диагноз [%s]: %s", diagnosis, description);
    }

}
