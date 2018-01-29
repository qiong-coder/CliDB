package com.buaa.CliDB.service.impl;

import com.buaa.CliDB.entity.Disease;
import com.buaa.CliDB.exception.KeyDuplicateException;
import com.buaa.CliDB.exception.NotFoundException;
import com.buaa.CliDB.repository.DiseaseRepository;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import com.buaa.CliDB.service.DiseaseService;

import com.buaa.CliDB.utils.MergeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DiseaseService")
public class DiseaseServiceImpl implements DiseaseService {

    private static Logger logger = LoggerFactory.getLogger(DiseaseService.class);

    @Autowired private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> list(String patientId, boolean exception) {
        List<Disease> diseases = diseaseRepository.findDiseasesByPatientId(patientId);
        if ( exception && diseases.isEmpty() ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to find the diseases");
        return diseases;
    }

    @Override
    public Disease add(Disease disease) {
        try {
            return diseaseRepository.insert(disease);
        } catch (DuplicateKeyException e) {
            throw new KeyDuplicateException(ResponseStatusAndInfos.ERROR.getStatus(),
                    "failure to insert the disease cause of key duplicate");
        }
    }

    @Override
    public Disease update(Disease disease) {
        Disease disease1 = diseaseRepository.findOne(disease.getId());

        if ( disease1 == null ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to update the disease cause of not founded");

        disease = MergeUtils.mergeObjects(disease, disease1, Disease.class);

        if ( disease != null ) return diseaseRepository.save(disease);
        else return null;
    }

    @Override
    public Disease delete(String id) {
        Disease disease = diseaseRepository.findOne(id);
        if ( disease == null  ) throw new NotFoundException(ResponseStatusAndInfos.ERROR.getStatus(),
                "failure to delete disease cause of not founded");
        else {
            diseaseRepository.deleteDiseaseById(id);
            return disease;
        }
    }
}
