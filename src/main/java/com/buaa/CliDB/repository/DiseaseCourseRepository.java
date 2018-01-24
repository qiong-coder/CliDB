package com.buaa.CliDB.repository;

import com.buaa.CliDB.entity.DiseaseCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiseaseCourseRepository extends MongoRepository<DiseaseCourse, String>
        //,QuerydslPredicateExecutor<DiseaseCourse>
{

    List<DiseaseCourse> findDiseaseCoursesByDiseaseIdOrderByIndex(String diseaseId);

    long countDiseaseCoursesByDiseaseId(String diseaseId);
}
