package com.buaa.CliDB.logic.impl;

import com.buaa.CliDB.logic.FileUploadLogic;
import com.buaa.CliDB.utils.ImageCompressUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component("FileUploadLogic")
public class FileUploadLogicImpl implements FileUploadLogic {

    private String uploadDir;

    @Override
    public void setServletContext(ServletContext servletContext) {
        uploadDir = servletContext.getRealPath("/")+"../images";
        File dir = new File(uploadDir);
        if ( !dir.exists() ) dir.mkdirs();
    }

    @Override
    public List<String> save(String patientId, String index, Part[] files) {

        List<String> filenames = Lists.newArrayList();

        String saveDir = uploadDir + "/" + patientId + "/" + index + "/";

        File dir = new File(saveDir);

        if ( !dir.exists() ) dir.mkdirs();

        for ( Part file : files ) {
            if ( file.getSize() == 0 ) continue;

            String filename = file.getSubmittedFileName().substring(0,file.getSubmittedFileName().lastIndexOf("."));

            try {
                file.write(saveDir+file.getSubmittedFileName());
                ImageCompressUtils.compress(saveDir+file.getSubmittedFileName(), saveDir+filename+"_compress",
                        320,240, 1f);
            } catch (IOException e) {
                continue;
            }

            filenames.add(file.getSubmittedFileName());
        }

        return filenames;
    }

    @Override
    public void delete(String patientId, String index) {

        String deleteDir = uploadDir+ "/" + patientId + "/" + index + "/";

        File dir = new File(deleteDir);

        if ( dir.exists() ) dir.delete();

    }

}
