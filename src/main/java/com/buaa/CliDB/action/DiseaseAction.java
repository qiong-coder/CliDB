package com.buaa.CliDB.action;


import com.buaa.CliDB.entity.Disease;
import com.buaa.CliDB.response.ResponseBuilder;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import com.buaa.CliDB.service.DiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/disease")
public class DiseaseAction {

    private static Logger logger = LoggerFactory.getLogger(DiseaseAction.class);


    @Autowired private DiseaseService diseaseService;

    @RequestMapping(value = "/{patientId}/", method = RequestMethod.GET)
    public ResponseBuilder list(@PathVariable String patientId)
    {
        List<Disease> diseases = diseaseService.list(patientId, true);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, diseases);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseBuilder add(@RequestBody Disease disease) {
        disease = diseaseService.add(disease);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, disease);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseBuilder update(@RequestBody Disease disease) {
        disease = diseaseService.update(disease);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, disease);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseBuilder delete(@PathVariable String id) {
        Disease disease = diseaseService.delete(id);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, disease);
    }


}
