package model;

import java.time.LocalDate;
import java.util.Objects;

public final class Appointment {
    private final LocalDate date;
    private final Patient patient;
    private final Doctor doctor;

    public Appointment(LocalDate date, Patient patient, Doctor doctor){
        Objects.requireNonNull(date, "Дата приема не может быть пустым.");
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");

        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public LocalDate getDate(){
        return date.plus(doctor.getSpecialty().getTime());
    }



}
