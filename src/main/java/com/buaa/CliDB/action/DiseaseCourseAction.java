package com.buaa.CliDB.action;

 import com.alibaba.fastjson.JSONObject;
 import com.buaa.CliDB.entity.DiseaseCourse;
 import com.buaa.CliDB.response.ResponseBuilder;
 import com.buaa.CliDB.response.ResponseStatusAndInfos;

 import com.buaa.CliDB.service.DiseaseCourseService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.web.bind.annotation.*;

 import javax.annotation.Resource;
 import java.util.List;

@RestController
@RequestMapping(value = "/diseaseCourse")
public class DiseaseCourseAction {

    private static Logger logger = LoggerFactory.getLogger(DiseaseCourseAction.class);

    @Resource
    DiseaseCourseService diseaseCourseService;

    @RequestMapping(value = "/{diseaseId}/", method = RequestMethod.GET)
    public ResponseBuilder list(@PathVariable String diseaseId) {
        List<DiseaseCourse> diseaseCourseList = diseaseCourseService.list(diseaseId);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, diseaseCourseList);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseBuilder add(@RequestBody DiseaseCourse diseaseCourse) {
        diseaseCourse = diseaseCourseService.add(diseaseCourse);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, diseaseCourse);
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
    public ResponseBuilder delete(@PathVariable String id) {
        DiseaseCourse diseaseCourse = diseaseCourseService.delete(id);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, diseaseCourse);
    }

    @RequestMapping(value = "/{id}/{partId}/", method = RequestMethod.DELETE)
    public ResponseBuilder delete(@PathVariable String id,
                                  @PathVariable String partId) {
        DiseaseCourse diseaseCourse = diseaseCourseService.delete(id,partId);
        return new ResponseBuilder(ResponseStatusAndInfos.OK, diseaseCourse);
    }

    @RequestMapping(value = "/", method =  RequestMethod.PUT)
    public ResponseBuilder update(@RequestBody DiseaseCourse diseaseCourse) {
        diseaseCourse = diseaseCourseService.update(diseaseCourse);
        return new ResponseBuilder(ResponseStatusAndInfos.OK,diseaseCourse);
    }

}

