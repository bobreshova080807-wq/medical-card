package com.university.medical.model;


import java.time.Duration;

public enum Specialty {

    THERAPIST ("101", Duration.ofMinutes(15)),
    PSYCHIATRIST ("412", Duration.ofMinutes(30)),
    OTORHINOLARYNGOLOGIST("105", Duration.ofMinutes(15)),
    NEUROLOGIST ("407", Duration.ofMinutes(15)),
    OPHTHALMOLOGIST ("112", Duration.ofMinutes(10)),
    DENTIST("202", Duration.ofMinutes(40)),
    SURGEON("305", Duration.ofMinutes(20)),
    ALLERGIST("107", Duration.ofMinutes(15)),
    NARCOLOGIST("301", Duration.ofMinutes(15)),
    DERMATOLOGIST("216", Duration.ofMinutes(10)),
    GASTROENTEROLOGIST("502",Duration.ofMinutes(60));


    private final String cabinet;
    private final Duration time;

    Specialty(String cabinet, Duration time){

        this.cabinet = cabinet;
        this.time = time;
    }

    public String getCabinet() {
        return cabinet;
    }

    public Duration getTime() {
        return time;
    }
}
