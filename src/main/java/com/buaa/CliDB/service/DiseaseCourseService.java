package com.buaa.CliDB.service;

import com.buaa.CliDB.entity.DiseaseCourse;

import javax.servlet.http.Part;
import java.util.List;

public interface DiseaseCourseService {

    List<DiseaseCourse> list(String diseaseId);

    DiseaseCourse add(DiseaseCourse diseaseCourse);

    DiseaseCourse update(DiseaseCourse diseaseCourse);

    DiseaseCourse delete(String id);

    DiseaseCourse delete(String id, String partId);

}
