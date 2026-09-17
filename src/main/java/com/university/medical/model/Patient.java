package com.university.medical.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Patient {
    private final String name;
    private final String policy;
    private final LocalDate birthday;

    public Patient(String name, String policy, LocalDate birthday) {

        Objects.requireNonNull(name, "Имя пациента не может быть пустым.");
        Objects.requireNonNull(policy, "Номер полиса не может быть пустым.");
        Objects.requireNonNull(birthday, "Дата рождения не может быть пустой.");

        if (name.isBlank()) {
            throw new IllegalArgumentException("Имя пациента не может быть пустым");
        }
        if (policy.isBlank()) {
            throw new IllegalArgumentException("Номер полиса не может быть пустым");
        }

        this.name = name;
        this.policy = policy;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getPolicy() {
        return policy;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Patient) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.policy, that.policy) &&
                Objects.equals(this.birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, policy, birthday);
    }

    @Override
    public String toString() {
        return "Patient[" +
                "name=" + name + ", " +
                "policy=" + policy + ", " +
                "birthday=" + birthday + ']';
    }
}