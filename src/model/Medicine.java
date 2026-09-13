package model;

import java.util.Objects;

public final class Medicine {

    private final String name;
    private final String ingredient;

    public Medicine(String name, String ingredient){
        Objects.requireNonNull(name, "Название препарата не может быть пустым.");
        Objects.requireNonNull(ingredient, "Активное вещество не может быть пустым.");

        if(name.isBlank()){
            throw new IllegalArgumentException("Название препарата не может быть пустым.");
        }

        if(ingredient.isBlank()){
            throw new IllegalArgumentException("Активное вещество не может быть пустым.");
        }

        this.name = name;
        this.ingredient = ingredient;
    }

    public String getName(){
        return name;
    }

    public String getIngredient(){
        return ingredient;
    }

}
