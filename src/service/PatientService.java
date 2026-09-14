package service;

import exception.ConflictExclusion;
import model.Appointment;
import model.Doctor;
import model.Patient;


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

        if (startTime.isBefore(workStart) || endTime.isAfter(endTime)) {
            throw new ConflictExclusion(
                    String.format(
                            "Ваш прием выходит за рабочие часы работы кабинет: %s (с %s до %s)." +
                                    "Ваше запрошенное время: %s - %s." +
                                    "Пожалуйста, измените время вашего визита.",
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
                                "К сожалению, врач %s уже занят с %s до %s." +
                                        "Ваше запрошенное время: %s - %s." +
                                        "Пожалуйста, измените время вашего визита.",
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

    public List<Appointment> patientHistory(Patient patient, LocalDateTime startPeriod, LocalDateTime endPeriod) {

        List<Appointment> result = new ArrayList<>();

        for (Appointment appointment : appointments){
            LocalDateTime appointmentDate = appointment.getStartDateTime();

            if(!appointmentDate.isBefore(startPeriod) && !appointmentDate.isAfter(endPeriod)){
                result.add(appointment);
            }

        }

        return List.copyOf(result);

    }

    public List<Appointment> allAppointments(){
        return List.copyOf(appointments);
    }


}
