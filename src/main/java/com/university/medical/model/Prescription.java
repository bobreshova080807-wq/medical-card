package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public record Prescription (
    Medicine medicine,
    LocalDate start,
    int days,
    int numberOfTimes
){
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

    public LocalDate getEnd(){
        return start.plusDays(days - 1);
    }

    public boolean rightDate(LocalDate date){
        return !date.isBefore(start) && !date.isAfter(getEnd());
    }


}
