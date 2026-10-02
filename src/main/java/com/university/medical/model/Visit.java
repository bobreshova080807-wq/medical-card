package com.university.medical.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Visit(
    Patient patient,
    Doctor doctor,
    LocalDateTime date,
    List<MedicalRecord> records
){
    public Visit{
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");
        Objects.requireNonNull(date, "Дата визита не может быть пустой.");
        Objects.requireNonNull(records, "Список записей не может быть пустым.");

    }

    public List<MedicalRecord> getRecords(){
        return Collections.unmodifiableList(records);
    }

    public List<DiagnosisRecord> getDiagnosisRecord(){
        List<DiagnosisRecord> result = new ArrayList<>();
        for (MedicalRecord record: records){
            if(record instanceof DiagnosisRecord diagnosisRecord){
                result.add(diagnosisRecord);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<PrescriptionRecord> getPrescriptionRecord(){
        List<PrescriptionRecord> result = new ArrayList<>();
        for (MedicalRecord record: records){
            if(record instanceof PrescriptionRecord prescriptionRecord){
                result.add(prescriptionRecord);
            }
        }
        return Collections.unmodifiableList(result);
    }


}
