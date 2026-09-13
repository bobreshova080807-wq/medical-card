package model;

import java.time.LocalDate;
import java.util.Objects;

public final class Prescription {
    private final Medicine medicine;
    private final LocalDate start;
    private final int days;
    private final int numberOfTimes;

    public Prescription(Medicine medicine, LocalDate start, int days, int numberOfTimes){
        Objects.requireNonNull(medicine, "Название препарата не может быть пустым.");
        Objects.requireNonNull(start, "Дата начала не может быть пустой.");

        if (days <= 0){
            throw new IllegalArgumentException("Длительность курса лечения не может быть пустой.");
        }

        if (numberOfTimes <= 0){
            throw new IllegalArgumentException("Количество приема прерарата в день не может быть пустым.");
        }

        this.medicine = medicine;
        this.start = start;
        this.days = days;
        this.numberOfTimes = numberOfTimes;

    }

    public LocalDate getEnd(){
        return start.plusDays(days - 1);
    }

    public boolean rightDate(LocalDate date){
        return !date.isBefore(start) && !date.isAfter(getEnd());
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public LocalDate getStart() {
        return start;
    }

    public int getDays() {
        return days;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Prescription) obj;
        return Objects.equals(this.medicine, that.medicine) &&
                Objects.equals(this.start, that.start) &&
                this.days == that.days &&
                this.numberOfTimes == that.numberOfTimes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicine, start, days, numberOfTimes);
    }

    @Override
    public String toString() {
        return "Prescription[" +
                "medicine=" + medicine.getName() + ", " +
                "start=" + start + ", " +
                "days=" + days + ", " +
                "numberOfTimes=" + numberOfTimes + ']';
    }

}
