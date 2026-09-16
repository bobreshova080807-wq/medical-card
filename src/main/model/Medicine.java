package main.model;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Medicine) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredient);
    }

    @Override
    public String toString() {
        return "Medicine[" +
                "name=" + name + ", " +
                "ingredient=" + ingredient + ']';
    }
}
