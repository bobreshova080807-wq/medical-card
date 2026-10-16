package com.university.medical.model;

import java.time.LocalDate;

/**
 * Перечисление стандартных лекарств с предустановленными параметрами курса.
 * Каждое лекарство имеет название, активное вещество, стандартную длительность
 * курса и частоту приёма.
 */

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

    /**
     * Конструктор стандартного лекарства.
     *
     * @param name          название лекарства
     * @param ingredient    активное вещество
     * @param days          стандартная длительность курса в днях
     * @param numberOfTimes стандартная частота приёма в день
     */

    ListMedicine(String name, String ingredient, int days, int numberOfTimes){
        this.name = name;
        this.ingredient = ingredient;
        this.days = days;
        this.numberOfTimes = numberOfTimes;
    }

    /**
     * Возвращает название лекарства.
     *
     * @return название лекарства
     */
    public String getName(){
        return name;
    }

    /**
     * Возвращает активное вещество.
     *
     * @return МНН активного вещества
     */
    public String getIngredient(){
        return ingredient;
    }

    /**
     * Возвращает стандартную длительность курса.
     *
     * @return количество дней курса
     */
    public int getDays(){
        return days;
    }

    /**
     * Возвращает стандартную частоту приёма.
     *
     * @return количество приёмов в день
     */
    public int getPeriod(){
        return numberOfTimes;
    }

    /**
     * Создаёт объект Medicine на основе этого стандартного лекарства.
     *
     * @return новый объект Medicine
     */
    public Medicine getMedicine(){
        return new Medicine(name, ingredient);
    }

    /**
     * Создаёт назначение со стандартными параметрами курса.
     *
     * @param start  дата начала приёма
     * @return новое назначение
     */
    public Prescription standartPrescription(LocalDate start){
        return new Prescription(getMedicine(), start, days, numberOfTimes);
    }

}
