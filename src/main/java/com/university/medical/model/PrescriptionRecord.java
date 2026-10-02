package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public record PrescriptionRecord(LocalDate date, Prescription prescription) implements MedicalRecord {
    public PrescriptionRecord {
        Objects.requireNonNull(date, "Дата не может быть null");
        Objects.requireNonNull(prescription, "Назначение не может быть null");
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return "Назначено: " + prescription.medicine().name() +
                " (" + prescription.medicine().ingredient() + ") на " +
                prescription.days() + " дней, " + prescription.numberOfTimes() + " раз(а) в день";
    }
}