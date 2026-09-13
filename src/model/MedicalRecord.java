package model;

import java.time.LocalDate;

public sealed interface MedicalRecord permits DiagnosisRecord, PrescriptionRecord {

    LocalDate getDate();

    String getDescription();
}