package com.buaa.CliDB.action;


import com.buaa.CliDB.logic.FileUploadLogic;
import com.buaa.CliDB.response.ResponseBuilder;
import com.buaa.CliDB.response.ResponseStatusAndInfos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
public class ImageAction {

    private static Logger logger =LoggerFactory.getLogger(ImageAction.class);

    @Autowired
    FileUploadLogic fileUploadLogic;

    @RequestMapping(value = "/{patientId}/{diseaseId}/", method = RequestMethod.POST)
    public ResponseBuilder add(@PathVariable String patientId,
                               @PathVariable String diseaseId,
                               @RequestPart Part[] images) {
        List<String> images_path = fileUploadLogic.save(patientId, diseaseId, images);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, images_path);
    }
}
