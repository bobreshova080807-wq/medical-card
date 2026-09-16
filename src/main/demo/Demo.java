package main.demo;

import main.exception.ConflictExclusion;
import main.model.*;
import main.service.DoctorService;
import main.service.PatientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Медицинская карта пациента \n");

        PatientService patientService = new PatientService();
        DoctorService doctorService = new DoctorService();

        System.out.println("1)Пациенты и врачи ******* \n");

        Patient patient1 = new Patient("Иванов Иван Иванович", "3478900753217465", LocalDate.of(2000, 10,10));
        Patient patient2 = new Patient("Петрова Анастасия Александровна", "8769000127589023", LocalDate.of(2008, 11,23));
        Patient patient3 = new Patient("Кузнецов Игорь Ватальевич", "7890003648997865", LocalDate.of(1987, 3,30));
        Patient patient4 = new Patient("Заяцева Мария Викторовна", "2789655778993302", LocalDate.of(1966, 2,15));
        Patient patient5 = new Patient("Артемов Александр Александрович", "1787665983200987", LocalDate.of(2002, 5,1));

        Doctor doctor1 = new Doctor("Лобанов Семен Семенович", "7649", Specialty.OPHTHALMOLOGIST);
        Doctor doctor2 = new Doctor("Романенко Глеб Викторович", "4328", Specialty.NARCOLOGIST);
        Doctor doctor3 = new Doctor("Быков Андрей Евгеньевич", "1985", Specialty.THERAPIST);
        Doctor doctor4 = new Doctor("Смирнов Аркадий Павлович", "6900", Specialty.DERMATOLOGIST);
        Doctor doctor5 = new Doctor("Рижская Маргарита Александровна", "8221", Specialty.PSYCHIATRIST);

        System.out.println("Создано 5 пациентов: \n");
        System.out.println("  1. " + patient1);
        System.out.println("  2. " + patient2);
        System.out.println("  3. " + patient3);
        System.out.println("  4. " + patient4);
        System.out.println("  5. " + patient5);
        System.out.println();

        System.out.println("Создано 5 врачей: \n");
        System.out.println("  1. " + doctor1);
        System.out.println("  2. " + doctor2);
        System.out.println("  3. " + doctor3);
        System.out.println("  4. " + doctor4);
        System.out.println("  5. " + doctor5);
        System.out.println();

        System.out.println("2)Запись на прием ******\n");

        Appointment appointment1 = new Appointment(LocalDateTime.of(2026, 10, 7, 9,30), patient1, doctor3);
        System.out.println("Запись 1: " + patient1.getName() + " записан(а) к следующему специалисту: " + doctor3.getName() + " на " + appointment1.getStartDateTime());

        Appointment appointment2 = new Appointment(LocalDateTime.of(2026, 9, 20, 10,40), patient2, doctor5);
        System.out.println("Запись 2: " + patient2.getName() + " записан(а) к следующему специалисту: " + doctor5.getName() + " на " + appointment2.getStartDateTime());

        Appointment appointment3 = new Appointment(LocalDateTime.of(2026, 10, 8, 12,10), patient3, doctor3);
        System.out.println("Запись 3: " + patient3.getName() + " записан(а) к следующему специалисту: " + doctor3.getName() + " на " + appointment3.getStartDateTime());

        Appointment appointment4 = new Appointment(LocalDateTime.of(2026, 10, 7, 10,30), patient4, doctor4);
        System.out.println("Запись 4: " + patient4.getName() + " записан(а) к следующему специалисту: " + doctor4.getName() + " на " + appointment4.getStartDateTime());

        Appointment appointment5 = new Appointment(LocalDateTime.of(2026, 10, 12, 17,0), patient5, doctor2);
        System.out.println("Запись 5: " + patient5.getName() + " записан(а) к следующему специалисту: " + doctor2.getName() + " на " + appointment5.getStartDateTime());

        System.out.println();

        System.out.println("3)Проверка коррекности записи ******\n");

        System.out.println("1.Попытка записи на занятое время: ");
        try {
            patientService.bookAppointment(LocalDateTime.of(2026, 10,7,9,20), patient2, doctor3);
            System.out.println("Ошибка: запись не должна была пройти");
        }catch (ConflictExclusion e){
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();

        System.out.println("2.Попытка записи до начала рабочего дня: ");
        try {
            patientService.bookAppointment(LocalDateTime.of(2026, 10,7,7,0), patient1, doctor2);
            System.out.println("Ошибка: запись не должна была пройти");
        }catch (ConflictExclusion e){
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();

        System.out.println("3.Попытка записи после конца рабочего дня: ");
        try {
            patientService.bookAppointment(LocalDateTime.of(2026, 10,7,21,20), patient4, doctor5);
            System.out.println("Ошибка: запись не должна была пройти");
        }catch (ConflictExclusion e){
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();

        System.out.println("4.Попытка записи встык с другим пациентом: ");
        try {
            patientService.bookAppointment(LocalDateTime.of(2026, 10,7,17,30), patient3, doctor5);
            Appointment appointment6 = new Appointment(LocalDateTime.of(2026,10,7,17,30), patient3, doctor5);
            System.out.println("Запись 6: " + patient3.getName() + " записан(а) к следующему специалисту: " + doctor5.getName() + "на " + appointment6.getStartDateTime());
        }catch (ConflictExclusion e){
            System.out.println("Исключение: " + e.getMessage());
        }
        System.out.println();

        System.out.println("4)История приемов пациента *****\n");
        System.out.println("Приемы пациента " + patient1.getName() + " - за сентябрь-октябрь: ");
        List<Appointment> history1 = patientService.patientHistory(patient1, LocalDate.of(2026,9,1), LocalDate.of(2026,10,31));
        if (history1.isEmpty()){
            System.out.println("Приемов за этот месяц не найдено\n");
        } else {
            for(Appointment appointment: history1){
                System.out.println("* " + appointment.getStartDateTime() + " - " + appointment.getDoctor().getName() + " - " + appointment.getDoctor().getSpecialty());
            }
        }
        System.out.println();

        System.out.println("5)Проведение приемов *****\n");
        Visit visit1 = doctorService.conductVisit(appointment1.getStartDateTime(), appointment1.getDoctor(), appointment1.getPatient(), "ОРВИ, острый ранит",
                List.of(ListMedicine.PARACETAMOL.standartPrescription(LocalDate.of(2026, 10,7)),
                       (ListMedicine.IBUPROFEN.standartPrescription(LocalDate.of(2026,10,7)))));
        System.out.println("Визит 1: " + patient1.getName());
        System.out.println("Диагноз: " + visit1.getDiagnosisRecord().getFirst().getDescriptionText());
        System.out.println("Назначение: " + visit1.getPrescriptionRecord() + "\n");

        System.out.println("6)Действующие назначения на дату *****\n");
        LocalDate check = LocalDate.of(2026,10,7);
        System.out.println("Действующие назначения пациента " + appointment1.getPatient().getName() + " на дату: " + check);
        List<Prescription> active1 = doctorService.prescriptionHistory(appointment1.getPatient(), check);
        if(active1.isEmpty()){
            System.out.println("На выбранную дату нет никаких назначений");
        } else {
            for(Prescription prescription: active1){
                System.out.println("* " + prescription.getMedicine().getName() + " до " + prescription.getEnd());
            }
        }
        System.out.println();

        System.out.println("7) Поиск пересекающихся значений *****\n");
        System.out.println("Проверка пациента " + patient1.getName() + " на дату " + check);
        List<DoctorService.PairMedicine> pairMedicines = doctorService.havePairMedicine(patient1, check);

        if(pairMedicines.isEmpty()){
            System.out.println("Пересечений нет");
        } else{
            System.out.println("Найдены пересечения по активному веществу: ");
            for(DoctorService.PairMedicine pair: pairMedicines){
                System.out.println("* " + pair.first().getMedicine() + " и " + pair.second().getMedicine());
                System.out.println("Вещество: " + pair.first().getMedicine().getIngredient());
            }
        }
        System.out.println();

        System.out.println("8)Медецинские записи *****\n");
        System.out.println("Визит пациента " + patient1.getName() + "(все визиты): ");
        doctorService.printVisitRecords(visit1);
        System.out.println();

        System.out.println("9)Полная история визитов *****\n");
        List<Visit> visits = doctorService.allHistoryVisits();
        System.out.println("Все визиты: " + visits.size());
        for(Visit visit: visits){
            System.out.println("* " + visit.getDate() + " - " +
                    visit.getPatient().getName() + " - " +
                    visit.getDoctor().getName() + " - " +
                    visit.getDiagnosisRecord().size() + " диагноз(ов)" + " - " +
                    visit.getPrescriptionRecord().size() + " назначение(й/я)");
        }
        System.out.println();


    }
}
