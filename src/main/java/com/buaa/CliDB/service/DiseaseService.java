package com.buaa.CliDB.service;

import com.buaa.CliDB.entity.Disease;

import java.util.List;

public interface DiseaseService {

    List<Disease> list(String patientId, boolean exception);

    Disease add(Disease disease);

    Disease update(Disease disease);

    Disease delete(String id);

}
