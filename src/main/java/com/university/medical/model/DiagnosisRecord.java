package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public record DiagnosisRecord (
    LocalDate date,
    String diagnosis,
    String description

) implements MedicalRecord {
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

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return String.format("Диагноз [%s]: %s", diagnosis, description);
    }

}
