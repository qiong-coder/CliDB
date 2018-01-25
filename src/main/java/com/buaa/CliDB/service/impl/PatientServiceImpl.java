package com.buaa.CliDB.service.impl;

import com.buaa.CliDB.entity.Patient;
import com.buaa.CliDB.exception.KeyDuplicateException;
import com.buaa.CliDB.exception.NotFoundException;
import com.buaa.CliDB.repository.PatientRepository;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import com.buaa.CliDB.service.PatientService;
import com.buaa.CliDB.utils.MergeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PatientService")
public class PatientServiceImpl implements PatientService {

    @Autowired PatientRepository patientRepository;

    @Override
    public List<Patient> list(String doctorId) {
        List<Patient> patients = patientRepository.findPatientsByDoctorId(doctorId);
        if ( patients.isEmpty() ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to find patients");
        else return patients;
    }

    @Override
    public Patient add(Patient patient) {

        try {
            return patientRepository.insert(patient);
        } catch (DuplicateKeyException e) {
            throw new KeyDuplicateException(ResponseStatusAndInfos.ERROR.getStatus(),
                    "failure to add patient cause of key duplicate");
        }

    }

    @Override
    public Patient delete(String id) {
        Patient patient = patientRepository.findOne(id);
        if ( patient == null ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to delete the patient cause of not founded");
        else {
            patientRepository.deletePatientById(id);
            return patient;
        }
    }

    @Override
    public Patient update(Patient patient) {
        Patient patient1 = patientRepository.findOne(patient.getId());

        if ( patient1 == null ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to update the patient cause of not founded");

        patient = MergeUtils.mergeObjects(patient,patient1);

        if ( patient != null ) return patientRepository.save(patient);
        return null;
    }
}
