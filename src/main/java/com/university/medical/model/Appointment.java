package com.university.medical.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Запись пациента на приём к врачу.
 * Содержит дату и время начала, пациента и врача.
 *
 * @param date     дата и время начала приёма
 * @param patient  пациент
 * @param doctor   врач
 */

public record Appointment (
    LocalDateTime date,
    Patient patient,
    Doctor doctor
){
    /**
     * Компактный конструктор с валидацией.
     *
     * @throws NullPointerException если любое поле null
     */
    public Appointment {
        Objects.requireNonNull(date, "Дата приема не может быть пустым.");
        Objects.requireNonNull(patient, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(doctor, "Имя врача не может быть пустым.");
    }

    /**
     * Возвращает дату и время окончания приёма.
     * Вычисляется как начало + длительность специальности врача.
     *
     * @return дата и время окончания приёма
     */
    public LocalDateTime getEndDateTime(){
        return date.plus(doctor.specialty().getTime());
    }
}
