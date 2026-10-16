package com.university.medical.service;

import com.university.medical.exception.ConflictExclusion;
import com.university.medical.model.Appointment;
import com.university.medical.model.Doctor;
import com.university.medical.model.Patient;
import com.university.medical.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для PatientService.
 */
class PatientServiceTest {

    private PatientService patientService;
    private Patient patient1;
    private Patient patient2;
    private Doctor doctor1;
    private Doctor doctor2;

    /**
     * Подготовка тестовых данных перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        patientService = new PatientService();
        patient1 = new Patient("111", "Иванов Иван", LocalDate.of(1990, 1, 1));
        patient2 = new Patient("222", "Петров Пётр", LocalDate.of(1985, 5, 5));
        doctor1 = new Doctor("D1", "Смирнов А.П.", Specialty.THERAPIST);
        doctor2 = new Doctor("D2", "Козлова Е.В.", Specialty.SURGEON);
    }

    /**
     * Тест успешной записи на приём.
     */
    @Test
    void bookAppointment_success() {
        Appointment apt = patientService.bookAppointment(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                patient1,
                doctor1
                );

        assertNotNull(apt);
        assertEquals(doctor1, apt.doctor());
        assertEquals(patient1, apt.patient());
    }

    /**
     * Тест конфликта при записи к занятому врачу.
     */
    @Test
    void bookAppointment_sameDoctorSameTime() {
        patientService.bookAppointment(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                patient1,
                doctor1
                );

        ConflictExclusion e = assertThrows(
                ConflictExclusion.class,
                () -> patientService.bookAppointment(
                        LocalDateTime.of(2026, 9, 15, 10, 10),
                        patient2,
                        doctor1
                                )
        );
        assertTrue(e.getMessage().contains("уже занят"));
    }

    /**
     * Тест конфликта при записи до рабочих часов.
     */
    @Test
    void bookAppointment_beforeWorkingHours() {
        assertThrows(
                ConflictExclusion.class,
                () -> patientService.bookAppointment(
                        LocalDateTime.of(2026, 9, 15, 7, 30),
                        patient1,
                        doctor1)
        );
    }

    /**
     * Тест конфликта при записи после рабочих часов.
     */
    @Test
    void bookAppointment_afterWorkingHours() {
        assertThrows(
                ConflictExclusion.class,
                () -> patientService.bookAppointment(
                        LocalDateTime.of(2026, 9, 15, 19, 50),
                        patient1,
                        doctor2
                                )
        );
    }

    /**
     * Тест успешной записи встык.
     */
    @Test
    void bookAppointment_backToBack_success() { //впритык
        patientService.bookAppointment(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                patient1,
                doctor1
                );

        Appointment apt2 = patientService.bookAppointment(
                LocalDateTime.of(2026, 9, 15, 10, 15),
                patient2,
                doctor1
                );

        assertNotNull(apt2);
    }

    /**
     * Тест фильтрации истории по периоду.
     */
    @Test
    void getPatientAppointments_filterPeriod() {

        patientService.bookAppointment(LocalDateTime.of(2026, 9, 14, 10, 0), patient1, doctor1);
        patientService.bookAppointment(LocalDateTime.of(2026, 9, 15, 10, 0), patient1, doctor1);
        patientService.bookAppointment(LocalDateTime.of(2026, 9, 20, 10, 0), patient1, doctor1);

        List<Appointment> history = patientService.patientHistory(
                patient1,
                LocalDate.of(2026, 9, 14),
                LocalDate.of(2026, 9, 20)
        );

        assertEquals(3, history.size());
    }

    /**
     * Тест пустой истории за период без записей.
     */
    @Test
    void getPatientAppointments_filterPeriodNoAppointments() {

        List<Appointment> history = patientService.patientHistory(
                patient1,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 31)
        );

        assertTrue(history.isEmpty());
    }
}