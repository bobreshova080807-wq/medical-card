package com.university.medical.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Appointment (
    LocalDateTime date,
    Patient patient,
    Doctor doctor
){
    public Appointment {
        Objects.requireNonNull(date, "Дата приема не может быть пустым.");
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");
    }

    public LocalDateTime getEndDateTime(){
        return date.plus(doctor.specialty().getTime());
    }
}
