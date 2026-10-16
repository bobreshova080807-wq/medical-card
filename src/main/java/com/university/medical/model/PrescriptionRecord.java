package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Запись о назначении лекарства в медицинской карте.
 *
 * @param date         дата назначения
 * @param prescription назначение
 */

public record PrescriptionRecord(LocalDate date, Prescription prescription) implements MedicalRecord {

    /**
     * Компактный конструктор с валидацией.
     *
     * @throws NullPointerException если date или prescription null
     */
    public PrescriptionRecord {
        Objects.requireNonNull(date, "Дата не может быть null");
        Objects.requireNonNull(prescription, "Назначение не может быть null");
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
        return "Назначено: " + prescription.medicine().name() +
                " (" + prescription.medicine().ingredient() + ") на " +
                prescription.days() + " дней, " + prescription.numberOfTimes() + " раз(а) в день";
    }
}