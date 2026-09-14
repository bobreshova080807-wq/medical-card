package model;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Appointment {
    private final LocalDateTime date;
    private final Patient patient;
    private final Doctor doctor;

    public Appointment(LocalDateTime date, Patient patient, Doctor doctor){
        Objects.requireNonNull(date, "Дата приема не может быть пустым.");
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");

        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public LocalDateTime getStartDateTime(){
        return date;
    }

    public LocalDateTime getEndDateTime(){
        return date.plus(doctor.getSpecialty().getTime());
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Appointment) obj;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.patient, that.patient) &&
                Objects.equals(this.doctor, that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, patient, doctor);
    }

    @Override
    public String toString() {
        return "Appointment[" +
                "startDateTime=" + date + ", " +
                "patient=" + patient.getName() + ", " +
                "doctor=" + doctor.getName() + ']';
    }
}
