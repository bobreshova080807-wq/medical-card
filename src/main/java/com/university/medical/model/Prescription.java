package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Назначение лекарства пациенту.
 * Содержит лекарство, дату начала курса, длительность и частоту приёма.
 *
 * @param medicine      лекарство
 * @param start         дата начала курса
 * @param days          длительность курса в днях
 * @param numberOfTimes частота приёма в день
 */
public record Prescription (
    Medicine medicine,
    LocalDate start,
    int days,
    int numberOfTimes
) {
    /**
     * Компактный конструктор с валидацией.
     *
     * @throws NullPointerException     если medicine или start null
     * @throws IllegalArgumentException если days или numberOfTimes <= 0
     */
    public Prescription {
        Objects.requireNonNull(medicine, "Название препарата не может быть пустым.");
        Objects.requireNonNull(start, "Дата начала не может быть пустой.");

        if (days <= 0){
            throw new IllegalArgumentException("Длительность курса лечения не может быть пустой.");
        }

        if (numberOfTimes <= 0){
            throw new IllegalArgumentException("Количество приема прерарата в день не может быть пустым.");
        }


    }

    /**
     * Возвращает дату окончания курса.
     *
     * @return дата окончания (start + days - 1)
     */
    public LocalDate getEnd(){
        return start.plusDays(days - 1);
    }

    /**
     * Проверяет, действует ли назначение на указанную дату.
     *
     * @param date дата для проверки
     * @return true, если дата в интервале [start, end]
     */
    public boolean rightDate(LocalDate date){
        return !date.isBefore(start) && !date.isAfter(getEnd());
    }


}
