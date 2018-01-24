package com.buaa.CliDB.repository;

import com.buaa.CliDB.entity.Disease;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiseaseRepository extends MongoRepository<Disease, String> {

    List<Disease> findDiseasesByPatientId(String patientId);

    long deleteDiseaseById(String id);

}
