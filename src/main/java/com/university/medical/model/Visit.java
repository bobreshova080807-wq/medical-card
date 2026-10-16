package com.university.medical.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Состоявшийся приём у врача.
 * Содержит дату, врача, пациента и список медицинских записей.
 *
 * @param date     дата и время приёма
 * @param doctor   врач
 * @param patient  пациент
 * @param records  список медицинских записей
 */

public record Visit(
    Patient patient,
    Doctor doctor,
    LocalDateTime date,
    List<MedicalRecord> records
){

    /**
     * Компактный конструктор с валидацией и созданием защитной копии списка.
     *
     * @throws NullPointerException если любое поле null
     */
    public Visit{
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");
        Objects.requireNonNull(date, "Дата визита не может быть пустой.");
        Objects.requireNonNull(records, "Список записей не может быть пустым.");

    }

    /**
     * Возвращает защитную копию списка медицинских записей.
     *
     * @return неизменяемый список записей
     */
    public List<MedicalRecord> getRecords(){
        return Collections.unmodifiableList(records);
    }

    /**
     * Возвращает только диагнозы из записей.
     *
     * @return неизменяемый список диагнозов
     */
    public List<DiagnosisRecord> getDiagnosisRecord(){
        List<DiagnosisRecord> result = new ArrayList<>();
        for (MedicalRecord record: records){
            if(record instanceof DiagnosisRecord diagnosisRecord){
                result.add(diagnosisRecord);
            }
        }
        return Collections.unmodifiableList(result);
    }

    /**
     * Возвращает только назначения из записей.
     *
     * @return неизменяемый список назначений
     */
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