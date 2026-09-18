package com.university.medical.service;

import com.university.medical.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceTest {

    private DoctorService doctorService;
    private Patient patient1;
    private Patient patient2;
    private Doctor doctor1;

    @BeforeEach
    void setUp() {
        doctorService = new DoctorService();
        patient1 = new Patient("111", "Иванов Иван", LocalDate.of(1990, 1, 1));
        patient2 = new Patient("222", "Петров Пётр", LocalDate.of(1985, 5, 5));
        doctor1 = new Doctor("D1", "Смирнов А.П.", Specialty.THERAPIST);
    }

    @Test
    void conductVisit_visitWithDiagnosisAndPrescriptions() {
        Visit visit = doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1,
                patient1,
                "ОРВИ",
                List.of(ListMedicine.PARACETAMOL.standartPrescription(
                        LocalDate.of(2026, 9, 15))
                )
        );

        assertNotNull(visit);
        assertEquals(patient1, visit.getPatient());
        assertEquals(doctor1, visit.getDoctor());
        assertEquals(1, visit.getDiagnosisRecord().size());
        assertEquals(1, visit.getPrescriptionRecord().size());
    }

    @Test
    void getActivePrescriptions_activeOnDate() {

        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1, patient1, "Диагноз 1",
                List.of(ListMedicine.PARACETAMOL.standartPrescription(
                        LocalDate.of(2026, 9, 15)))
        );
        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 16, 10, 0),
                doctor1, patient1, "Диагноз 2",
                List.of(ListMedicine.OMEGA3.standartPrescription(
                        LocalDate.of(2026, 9, 16)))
        );

        List<Prescription> active = doctorService.prescriptionHistory(
                patient1,
                LocalDate.of(2026, 9, 20)
        );

        assertEquals(1, active.size());
        assertEquals("Омега-3", active.get(0).getMedicine().getName());
    }

    @Test
    void getActivePrescriptions_noActive() {

        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1, patient1, "Диагноз",
                List.of(ListMedicine.PARACETAMOL.standartPrescription(
                        LocalDate.of(2026, 9, 15)))
        );

        List<Prescription> active = doctorService.prescriptionHistory(
                patient1,
                LocalDate.of(2026, 10, 15)
        );

        assertTrue(active.isEmpty());
    }

    @Test
    void detectsSameIngredient() {

        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1, patient1, "Гастрит",
                List.of(
                        ListMedicine.DENOL.standartPrescription(
                                LocalDate.of(2026, 9, 15)),  // bismuth
                        new Prescription(new Medicine("Улкозол", "bismuth"),
                                LocalDate.of(2026, 9, 15),
                                10, 2
                        )
                )
        );

        List<DoctorService.PairMedicine> intersections =
                doctorService.havePairMedicine(
                        patient1,
                        LocalDate.of(2026, 9, 16)
                );
        assertEquals(1, intersections.size());
    }

    @Test
    void noIntersectionWhenDifferentIngredients() {

        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1, patient1, "ОРВИ",
                List.of(
                        ListMedicine.PARACETAMOL.standartPrescription(
                                LocalDate.of(2026, 9, 15)),
                        ListMedicine.AMOXICILLIN.standartPrescription(
                                LocalDate.of(2026, 9, 15))
                )
        );

        List<DoctorService.PairMedicine> intersections =
                doctorService.havePairMedicine(
                        patient1,
                        LocalDate.of(2026, 9, 16)
                );


        assertTrue(intersections.isEmpty());
    }

    @Test
    void getPatientVisits_filtersByPatientAndPeriod() {

        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 15, 10, 0),
                doctor1, patient1, "Диагноз 1", List.of());
        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 16, 10, 0),
                doctor1, patient2, "Диагноз 2", List.of());
        doctorService.conductVisit(
                LocalDateTime.of(2026, 9, 20, 10, 0),
                doctor1, patient1, "Диагноз 3", List.of());

        List<Visit> visits = doctorService.historyVisits(
                patient1,
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 9, 30)
        );

        assertEquals(2, visits.size());
    }
}