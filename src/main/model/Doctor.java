package main.model;

import java.util.Objects;

public final class Doctor {
    private final String name;
    private final String id;
    private final Specialty specialty;

    public Doctor(String name, String id, Specialty specialty){
        Objects.requireNonNull(name, "Имя врача не может быть пустым.");
        Objects.requireNonNull(id, "ID врача не может быть пустым.");
        Objects.requireNonNull(specialty, "Специальность врача не может быть пустой.");

        if (name.isBlank()){
            throw new IllegalArgumentException("Имя врача не может быть пустым.");
        }

        if (id.isBlank()){
            throw new IllegalArgumentException("ID врача не может быть пустым.");
        }

        this.name = name;
        this.id = id;
        this.specialty = specialty;

    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Doctor) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.id, that.id) &&
                Objects.equals(this.specialty, that.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, specialty);
    }

    @Override
    public String toString() {
        return "Doctor[" +
                "name=" + name + ", " +
                "id=" + id + ", " +
                "specialty=" + specialty + ']';
    }
}
