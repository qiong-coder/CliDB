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
        DiseaseCourse diseaseCourse1 = diseaseCourseRepository.findOne(diseaseCourse.getId());

        if ( diseaseCourse1 == null ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to update the disease course cause of not founded");

        diseaseCourse = MergeUtils.mergeObjects(diseaseCourse, diseaseCourse1);

        if ( diseaseCourse == null ) return null;

        return diseaseCourseRepository.save(diseaseCourse);
    }

    @Override
    public DiseaseCourse delete(String id) {

        DiseaseCourse diseaseCourse = diseaseCourseRepository.findOne(id);

        if ( diseaseCourse != null ) diseaseCourseRepository.delete(id);
        else throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(), "failure to delete the disease course cause of not founded");

        return diseaseCourse;
    }

    @Override
    public DiseaseCourse delete(String id, String partId) {

        DiseaseCourse diseaseCourse = diseaseCourseRepository.findOne(id);

        if ( diseaseCourse == null ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(), "failure to delete the disease course's part cause of not founded");

        if ( partId.compareToIgnoreCase("mainAnswers") == 0 ) {
            diseaseCourse.setMainAnswers(null);
        } else if ( partId.compareToIgnoreCase("firstAnswers") == 0 ) {
            diseaseCourse.setFirstAnswers(null);
        } else if ( partId.compareToIgnoreCase("secondAnswers") == 0 ) {
            diseaseCourse.setSecondAnswers(null);
        } else if ( partId.compareToIgnoreCase("thirdAnswers") == 0 ) {
            diseaseCourse.setThirdAnswers(null);
        } else if ( partId.compareToIgnoreCase("fourthAnswers") == 0 ) {
            diseaseCourse.setFourthAnswers(null);
        } else {
            throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(), "failure to delete the disease course's part cause of partId is not correct");
        }

        return diseaseCourseRepository.save(diseaseCourse);
    }
}
