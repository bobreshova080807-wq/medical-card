package com.university.medical.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Visit {
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDateTime date;
    private final List<MedicalRecord> records;

    public Visit(Patient patient, Doctor doctor, LocalDateTime date, List<MedicalRecord> records){
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");
        Objects.requireNonNull(date, "Дата визита не может быть пустой.");
        Objects.requireNonNull(records, "Список записей не может быть пустым.");

        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.records = new ArrayList<>(records);
    }

    public Patient getPatient(){
        return patient;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public LocalDateTime getDate(){
        return date;
    }

    public List<MedicalRecord> getRecords(){
        return Collections.unmodifiableList(records);
    }

    public List<DiagnosisRecord> getDiagnosisRecord(){
        List<DiagnosisRecord> result = new ArrayList<>();
        for (MedicalRecord record: records){
            if (record instanceof DiagnosisRecord diagnosis){
                result.add(diagnosis);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<PrescriptionRecord> getPrescriptionRecord(){
        List<PrescriptionRecord> result = new ArrayList<>();
        for (MedicalRecord record: records){
            if (record instanceof PrescriptionRecord prescription){
                result.add(prescription);
            }
        }
        return Collections.unmodifiableList(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Visit) obj;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.doctor, that.doctor) &&
                Objects.equals(this.patient, that.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, doctor, patient);
    }

    @Override
    public String toString() {
        return "Visit[" +
                "dateTime=" + date + ", " +
                "doctor=" + doctor.getName() + ", " +
                "patient=" + patient.getName() + ", " +
                "records=" + records.size() + " шт.]";
    }
}
