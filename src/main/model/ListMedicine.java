package main.model;

import java.time.LocalDate;

public enum ListMedicine {

    PARACETAMOL("Парацетомол", "paracetamol",5, 2),
    IBUPROFEN("Ибупрофен", "ibuprofen", 7, 2),
    VITAMIN_D("", "vatimin d", 30, 1),
    OMEGA3("", "", 1, 1),
    HYPHENATION("", "", 1, 1),
    CITRINE("", "", 1, 1),
    ZERTEC("","", 1, 1),
    NUROFEN("","", 1,1),
    PHOSPHALGEL("","", 1, 2),
    PANCREATIN("","", 1, 2),
    DUSPATALIN("","", 1, 2),
    ESPUMIZAN("","", 1, 2),
    NOSHPA("","", 1, 2),
    QUETIAPINE("","", 1, 2),
    POLYSORB("","", 1, 2),
    AQUAMARIS("","", 1, 2),
    POLYDEX("","", 1, 2);


    private final String name;
    private final String ingredient;
    private final int days;
    private final int numberOfTimes;

    ListMedicine(String name, String ingredient, int days, int numberOfTimes){
        this.name = name;
        this.ingredient = ingredient;
        this.days = days;
        this.numberOfTimes = numberOfTimes;
    }

    public String getName(){
        return name;
    }

    public String getIngredient(){
        return ingredient;
    }

    public int getDays(){
        return days;
    }

    public int getPeriod(){
        return numberOfTimes;
    }

    public Medicine getMedicine(){
        return new Medicine(name, ingredient);
    }

    public Prescription standartPrescription(LocalDate start){
        return new Prescription(getMedicine(), start, days, numberOfTimes);
    }

}
