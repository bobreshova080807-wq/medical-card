package com.university.medical.model;

import java.time.LocalDate;

/**
 * Запечатанный интерфейс медицинской записи.
 * Может быть реализован только DiagnosisRecord или PrescriptionRecord.
 */
public sealed interface MedicalRecord permits DiagnosisRecord, PrescriptionRecord {

    /**
     * Возвращает дату записи.
     *
     * @return дата записи
     */
    LocalDate getDate();

    /**
     * Возвращает текстовое описание записи.
     *
     * @return описание записи
     */
    String getDescription();
}