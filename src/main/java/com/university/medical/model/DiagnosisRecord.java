package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public final class DiagnosisRecord implements MedicalRecord {

    private final LocalDate date;
    private final String diagnosis;
    private final String description;

    public DiagnosisRecord(LocalDate date, String diagnosis, String description){
        Objects.requireNonNull(date, "Дата предписания не может быть пустой.");
        Objects.requireNonNull(diagnosis, "Диагноз не может быть пустым.");
        Objects.requireNonNull(description, "Описание диагноза не может быть пустым.");

        if(diagnosis.isBlank()){
            throw new IllegalArgumentException("Диагноз не может быть пустым.");
        }

        if(diagnosis.isBlank()){
            throw new IllegalArgumentException("Описание диагноза не может быть пустым.");
        }

        this.date = date;
        this.diagnosis = diagnosis;
        this.description = description;

    }

    public LocalDate getDate(){
        return date;
    }

    public String getDiagnosis(){
        return diagnosis;
    }

    public String getDescriptionText(){
        return description;
    }

    @Override
    public String getDescription() {
        return String.format("Диагноз [%s]: %s", diagnosis, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DiagnosisRecord) obj;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.diagnosis, that.diagnosis) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, diagnosis, description);
    }

    @Override
    public String toString() {
        return "DiagnosisRecord[" +
                "date=" + date + ", " +
                "diagnosisCode=" + diagnosis + ", " +
                "description=" + description + ']';
    }


}
