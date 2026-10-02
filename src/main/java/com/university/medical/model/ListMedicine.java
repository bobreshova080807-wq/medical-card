package com.university.medical.model;

import java.time.LocalDate;

public enum ListMedicine {

    PARACETAMOL("Парацетамол", "paracetamol", 5, 3),
    IBUPROFEN("Ибупрофен", "ibuprofen", 7, 2),
    VITAMIN_D("Витамин D", "vitamin-d", 30, 1),
    OMEGA3("Омега-3", "omega-3-fatty-acids", 30, 1),
    HYPHENATION("Хьюмер", "sea-water", 14, 3),
    CITRINE("Цетрин", "cetirizine", 10, 1),
    ZERTEC("Зиртек", "cetirizine", 10, 1),
    NUROFEN("Нурофен", "ibuprofen", 5, 3),
    PHOSPHALGEL("Фосфалюгель", "aluminum-phosphate", 14, 3),
    PANCREATIN("Панкреатин", "pancreatin", 14, 3),
    DUSPATALIN("Дюспаталин", "mebeverine", 14, 2),
    ESPUMIZAN("Эспумизан", "simethicone", 7, 3),
    NOSHPA("Ношпа", "drotaverine", 5, 3),
    QUETIAPINE("Кветиапин", "quetiapine", 30, 2),
    POLYSORB("Полисорб", "colloidal-silicon-dioxide", 10, 3),
    AQUAMARIS("Аквамарис", "sea-water", 14, 4),
    POLYDEX("Полидекса", "neomycin-polymyxin-dexamethasone", 7, 2),
    DENOL("Денол", "bismuth", 14, 2),
    AMOXICILLIN("Амоксициллин", "amoxicillin", 10, 3);

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
