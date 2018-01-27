package com.buaa.CliDB.service;

import com.buaa.CliDB.entity.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> list(String doctorId, boolean exception);

    Patient add(Patient patient);

    Patient delete(String id);

    Patient update(Patient patient);

}
