package com.university.medical.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void patient_equalsAndHashCode_samePolicy() {
        Patient p1 = new Patient("111", "Иванов", LocalDate.of(1990, 1, 1));
        Patient p2 = new Patient("111", "Иванов", LocalDate.of(1990, 1, 1));

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void patient_equalsAndHashCode_differentPolicy() {
        Patient p1 = new Patient("111", "Иванов", LocalDate.of(1990, 1, 1));
        Patient p2 = new Patient("222", "Иванов", LocalDate.of(1990, 1, 1));

        assertNotEquals(p1, p2);
    }

    @Test
    void patient_throwsOnNullPolicy() {
        assertThrows(NullPointerException.class, () ->
                new Patient(null, "Иванов", LocalDate.of(1990, 1, 1))
        );
    }

    @Test
    void patient_throwsOnBlankPolicy() {
        assertThrows(IllegalArgumentException.class, () ->
                new Patient("   ", "Иванов", LocalDate.of(1990, 1, 1))
        );
    }

    @Test
    void prescription_rightDate_withPeriod() {
        Medicine med = new Medicine("Тест", "test");
        Prescription p = new Prescription(med, LocalDate.of(2026, 9, 15), 5, 3);

        assertTrue(p.rightDate(LocalDate.of(2026, 9, 15)));
        assertTrue(p.rightDate(LocalDate.of(2026, 9, 17)));
        assertTrue(p.rightDate(LocalDate.of(2026, 9, 19)));
    }

    @Test
    void prescription_rightDate_withNoPeriod() {
        Medicine med = new Medicine("Тест", "test");
        Prescription p = new Prescription(med, LocalDate.of(2026, 9, 15), 5, 3);

        assertFalse(p.rightDate(LocalDate.of(2026, 9, 14)));
        assertFalse(p.rightDate(LocalDate.of(2026, 9, 20)));
    }

    @Test
    void prescription_throwsOnZeroDays() {
        Medicine med = new Medicine("Тест", "test");
        assertThrows(IllegalArgumentException.class, () ->
                new Prescription(med, LocalDate.of(2026, 9, 15), 0, 3)
        );
    }
}