package com.university.medical.service;

import com.university.medical.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Сервис для проведения приёмов врачом.
 *
 * <p>Отвечает за:
 * <ul>
 *   <li>Проведение приёма (создание визита с диагнозом и назначениями)</li>
 *   <li>Историю приёмов пациента за период</li>
 *   <li>Действующие назначения на указанную дату</li>
 *   <li>Поиск пересекающихся назначений по активному веществу</li>
 * </ul>
 */

public class DoctorService {

    private final List<Visit> visits = new ArrayList<>();

    /**
     * Проводит приём: ставит диагноз и назначает лечение.
     *
     * @param date          дата и время приёма
     * @param doctor        врач, проводящий приём
     * @param patient       пациент
     * @param diagnosis     текст диагноза
     * @param prescriptions список назначений
     * @return созданный визит
     */
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

    /**
     * Возвращает историю приёмов пациента за период.
     *
     * @param patient  пациент
     * @param start    начало периода (включительно)
     * @param end      конец периода (включительно)
     * @return неизменяемый список визитов за период
     */
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

    /**
     * Возвращает все визиты.
     *
     * @return неизменяемый список всех визитов
     */
    public List<Visit> allHistoryVisits(){
        return Collections.unmodifiableList(visits);
    }

    /**
     * Возвращает действующие назначения пациента на указанную дату.
     *
     * @param patient пациент
     * @param date    дата для проверки
     * @return неизменяемый список действующих назначений
     */
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

    /**
     * Пара пересекающихся назначений.
     *
     * @param first  первое назначение
     * @param second второе назначение
     */
    public record PairMedicine(Prescription first, Prescription second){}

    /**
     * Ищет пересекающиеся назначения у пациента.
     * Пересечением считается ситуация, когда два назначения имеют
     * одинаковое активное вещество и действуют одновременно.
     *
     * @param patient пациент
     * @param date    дата для проверки
     * @return неизменяемый список пар пересекающихся назначений
     */
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

    /**
     * Выводит медицинские записи визита с использованием pattern matching.
     *
     * @param visit визит для вывода
     */
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