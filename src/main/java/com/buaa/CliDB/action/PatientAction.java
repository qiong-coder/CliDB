package com.buaa.CliDB.action;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.buaa.CliDB.entity.Disease;
import com.buaa.CliDB.entity.Patient;
import com.buaa.CliDB.response.ResponseBuilder;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import com.buaa.CliDB.service.DiseaseService;
import com.buaa.CliDB.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping(value = "/patient")
public class PatientAction {

    static private Logger logger = LoggerFactory.getLogger(PatientAction.class);

    @Resource PatientService patientService;

    @Resource
    DiseaseService diseaseService;

    @RequestMapping(value = "/{doctorId}/", method = RequestMethod.GET)
    public ResponseBuilder list(@PathVariable String doctorId) {
        List<Patient> patients = patientService.list(doctorId, true);

        JSONArray pInfos = new JSONArray();

        for ( Patient patient : patients ) {

            List<Disease> diseases = diseaseService.list(patient.getId(),false);

            JSONObject pInfo = new JSONObject();
            pInfo.put("patient", patient);
            pInfo.put("diseases", diseases);
            pInfos.add(pInfo);

        }

        return new ResponseBuilder(ResponseStatusAndInfos.OK,pInfos);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseBuilder add(@RequestBody  Patient patient) {
        patient = patientService.add(patient);
        return new ResponseBuilder(ResponseStatusAndInfos.OK,patient);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseBuilder update(@RequestBody Patient patient) {
        patient = patientService.update(patient);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, patient);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseBuilder delete(@PathVariable String id) {
        Patient patient = patientService.delete(id);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, patient);
    }

}
