package main.service;

import main.exception.ConflictExclusion;
import main.model.Appointment;
import main.model.Doctor;
import main.model.Patient;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private final LocalTime workStart = LocalTime.of(8, 0);
    private final LocalTime workEnd = LocalTime.of(19, 0);
    private final List<Appointment> appointments = new ArrayList<>();


    public Appointment bookAppointment(
            LocalDateTime dayAppointment,
            Patient patient,
            Doctor doctor
    ) {
        workingHours(dayAppointment, doctor);

        doctorEmployment(dayAppointment, doctor);

        Appointment newAppointment = new Appointment(dayAppointment, patient, doctor);
        appointments.add(newAppointment);

        return newAppointment;

    }

    private void workingHours(LocalDateTime dayAppointment, Doctor doctor) {
        LocalTime startTime = dayAppointment.toLocalTime();
        LocalDateTime endDateTime = dayAppointment.plus(doctor.getSpecialty().getTime());
        LocalTime endTime = endDateTime.toLocalTime();

        if (startTime.isBefore(workStart) || endTime.isAfter(workEnd)) {
            throw new ConflictExclusion(
                    String.format(
                            "Ваш прием выходит за рабочие часы работы кабинет: %s (с %s до %s).\n" +
                                    "Ваше запрошенное время: %s - %s.\n" +
                                    "Пожалуйста, измените время вашего визита.\n",
                            doctor.getSpecialty().getCabinet(),
                            workStart,
                            workEnd,
                            startTime,
                            endTime
                    )
            );
        }
    }

    private void doctorEmployment(LocalDateTime dayAppointment, Doctor doctor) {
        LocalDateTime start = dayAppointment;
        LocalDateTime end = dayAppointment.plus(doctor.getSpecialty().getTime());

        for (Appointment booking : appointments) {
            if (!booking.getDoctor().equals(doctor)) {
                continue;
            }

            LocalDateTime bookingStart = booking.getStartDateTime();
            LocalDateTime bookingEnd = booking.getEndDateTime();

            boolean firstConflict = !start.isBefore(bookingStart) && start.isBefore(bookingEnd);
            boolean secondConflict = end.isAfter(bookingStart) && !end.isAfter(bookingEnd);

            if (firstConflict || secondConflict) {
                throw new ConflictExclusion(
                        String.format(
                                "К сожалению, врач %s уже занят с %s до %s.\n" +
                                        "Ваше запрошенное время: %s - %s.\n" +
                                        "Пожалуйста, измените время вашего визита.\n",
                                doctor.getName(),
                                bookingStart,
                                bookingEnd,
                                start,
                                end
                        )
                );
            }


        }

    }

    public List<Appointment> patientHistory(Patient patient, LocalDate startPeriod, LocalDate endPeriod) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments){
            LocalDateTime appointmentDate = appointment.getStartDateTime();

            if(!appointmentDate.isBefore(startPeriod.atStartOfDay()) && !appointmentDate.isAfter(endPeriod.atStartOfDay())){
                result.add(appointment);
            }

        }

        return List.copyOf(result);

    }

    public List<Appointment> allAppointments(){
        return List.copyOf(appointments);
    }


}
