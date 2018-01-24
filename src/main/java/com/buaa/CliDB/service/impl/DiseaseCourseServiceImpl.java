package com.buaa.CliDB.service.impl;


import com.buaa.CliDB.entity.DiseaseCourse;
import com.buaa.CliDB.exception.KeyDuplicateException;
import com.buaa.CliDB.exception.NotFoundException;
import com.buaa.CliDB.logic.FileUploadLogic;
import com.buaa.CliDB.repository.DiseaseCourseRepository;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import com.buaa.CliDB.service.DiseaseCourseService;
import com.buaa.CliDB.utils.MergeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Optional;


@Service("DiseaseCourseService")
public class DiseaseCourseServiceImpl implements DiseaseCourseService {

    @Autowired
    DiseaseCourseRepository diseaseCourseRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<DiseaseCourse> list(String diseaseId) {
        List<DiseaseCourse> diseaseCourses = diseaseCourseRepository.findDiseaseCoursesByDiseaseIdOrderByIndex(diseaseId);
        if ( diseaseCourses.isEmpty() ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to find disease courses");
        else return diseaseCourses;
    }

    @Override
    public DiseaseCourse add(DiseaseCourse diseaseCourse) {

        diseaseCourse.setIndex(Long.toString(diseaseCourseRepository.countDiseaseCoursesByDiseaseId(diseaseCourse.getDiseaseId())));

        try {
            return diseaseCourseRepository.insert(diseaseCourse);
        } catch (DuplicateKeyException e) {
            throw new KeyDuplicateException(ResponseStatusAndInfos.ERROR.getStatus(),
                    "failure to insert the disease course cause of duplicate key");
        }

    }

    @Override
    public DiseaseCourse update(DiseaseCourse diseaseCourse) {
        Optional<DiseaseCourse> optionalDiseaseCourse = diseaseCourseRepository.findById(diseaseCourse.getId());

        if ( !optionalDiseaseCourse.isPresent() ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to update the disease course cause of not founded");

        diseaseCourse = MergeUtils.mergeObjects(diseaseCourse, optionalDiseaseCourse.get());

        if ( diseaseCourse == null ) return null;

        return diseaseCourseRepository.save(diseaseCourse);
    }

    @Override
    public DiseaseCourse delete(String id) {
        Optional<DiseaseCourse> diseaseCourse = diseaseCourseRepository.findById(id);

        if ( diseaseCourse.isPresent() ) diseaseCourseRepository.deleteById(id);
        else throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(), "failure to delete the disease course cause of not founded");

        return diseaseCourse.get();
    }
}
