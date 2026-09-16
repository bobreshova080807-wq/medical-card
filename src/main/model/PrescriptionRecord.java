package main.model;

import java.time.LocalDate;
import java.util.Objects;

public final class PrescriptionRecord implements MedicalRecord{

    private final LocalDate date;
    private final Prescription prescription;

    public PrescriptionRecord(LocalDate date, Prescription prescription){
        Objects.requireNonNull(date, "Дата предписания не может быть пустой.");
        Objects.requireNonNull(prescription, "Предписание не может быть пустым.");

        this.date = date;
        this.prescription = prescription;

    }

    public LocalDate getDate(){
        return date;
    }


    public Prescription getPrescriptionText(){
        return prescription;
    }

    @Override
    public String getDescription() {
        return String.format(
                "%s (%s) на %d дней, %d раз(а) в день",
                prescription.getMedicine().getName(),
                prescription.getMedicine().getIngredient(),
                prescription.getDays(),
                prescription.getNumberOfTimes()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PrescriptionRecord) obj;
        return Objects.equals(this.date, that.date) &&
                Objects.equals(this.prescription, that.prescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, prescription);
    }

    @Override
    public String toString() {
        return "Заключение (справка)[" +
                "Дата = " + date + ", " +
                "Заключение = " + prescription + ']';
    }

}
