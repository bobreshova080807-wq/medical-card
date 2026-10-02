package com.university.medical.service;

import com.university.medical.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DoctorService {

    private final List<Visit> visits = new ArrayList<>();

    public Visit conductVisit(LocalDateTime date, Doctor doctor, Patient patient, String diagnosis, List<Prescription> prescriptions ){
        List<MedicalRecord> records = new ArrayList<>();

        records.add(new DiagnosisRecord(date.toLocalDate(), "Заключение", diagnosis));

        for (Prescription prescription: prescriptions){
            records.add(new PrescriptionRecord(date.toLocalDate(), prescription));
        }

        Visit visit = new Visit(patient, doctor, date, records);
        visits.add(visit);

        return visit;

    }

    public List<Visit> historyVisits (Patient patient, LocalDate start, LocalDate end){
        List<Visit> result = new ArrayList<>();

        for(Visit visit: visits){
            if(!visit.patient().equals(patient)){
                continue;
            }
            LocalDate visitDate = visit.date().toLocalDate();

            if(!visitDate.isBefore(start) && !visitDate.isAfter(end)){
                result.add(visit);
            }
        }
        return Collections.unmodifiableList(result);
    }

    public List<Visit> allHistoryVisits(){
        return Collections.unmodifiableList(visits);
    }

    public List<Prescription> prescriptionHistory(Patient patient, LocalDate date){
        List<Prescription> result = new ArrayList<>();

        for(Visit visit: visits){
            if(!visit.patient().equals(patient)){
                continue;
            }

            for(PrescriptionRecord record: visit.getPrescriptionRecord()){
                if(record.prescription().rightDate(date)){
                    result.add(record.prescription());
                }
            }

        }
        return Collections.unmodifiableList(result);
    }

    public record PairMedicine(Prescription first, Prescription second){}

    public List<PairMedicine> havePairMedicine(Patient patient, LocalDate date){
        List<Prescription> active = prescriptionHistory(patient, date);
        List<PairMedicine> pair = new ArrayList<>();

        for(int i = 0; i < active.size(); i++){
            for(int j = i + 1; j < active.size(); j++){
                Prescription first = active.get(i);
                Prescription second = active.get(j);

                if(first.medicine().ingredient().equals(second.medicine().ingredient())){
                    pair.add(new PairMedicine(first, second));
                }
            }
        }
        return Collections.unmodifiableList(pair);
    }

    public void printVisitRecords(Visit visit) {
        System.out.println("Визит: " + visit.date());
        System.out.println("Пациент: " + visit.patient().name());
        System.out.println("Врач: " + visit.doctor().name());
        System.out.println("Записи:");

        for (MedicalRecord record : visit.getRecords()) {
            String output = switch (record) {
                case DiagnosisRecord d ->
                        "  [Диагноз] " + d.diagnosis() + ": " + d.description();
                case PrescriptionRecord p ->
                        "  [Назначение] " + p.prescription().medicine().name() +
                                " — " + p.prescription().days() + " дней, " +
                                p.prescription().numberOfTimes() + " раз(а) в день";
            };
            System.out.println(output);
        }
    }



}
