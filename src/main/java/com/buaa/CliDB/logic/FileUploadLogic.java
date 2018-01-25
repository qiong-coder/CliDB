package com.buaa.CliDB.logic;

import org.springframework.web.context.ServletContextAware;

import javax.servlet.http.Part;
import java.util.List;

public interface FileUploadLogic extends ServletContextAware {

    List<String> save(String patientId, String diseaseId, Part[] files);

    void delete(String patientId, String diseaseId);

}
