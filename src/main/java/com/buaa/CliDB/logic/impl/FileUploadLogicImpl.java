package com.buaa.CliDB.logic.impl;

import com.buaa.CliDB.logic.FileUploadLogic;
import com.buaa.CliDB.utils.ImageCompressUtils;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
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
    public List<String> save(String patientId, String diseaseId, Part[] files) {

        List<String> filenames = Lists.newArrayList();

        String saveDir = uploadDir + "/" + patientId + "/" + diseaseId + "/";

        File dir = new File(saveDir);

        if ( !dir.exists() ) dir.mkdirs();

        for ( Part file : files ) {
            if ( file.getSize() == 0 ) continue;

            String filename = file.getSubmittedFileName().substring(0,file.getSubmittedFileName().lastIndexOf("."));



            File compress_image = new File(saveDir+filename+"_compress.jpg");

            try {
                file.write(saveDir+file.getSubmittedFileName());
                if ( !ImageCompressUtils.compress(saveDir+file.getSubmittedFileName(), compress_image, 320,240, 0.5f) ) {
                    Files.copy(new File(saveDir+file.getSubmittedFileName()), compress_image);
                }
            } catch (IOException e) {
                continue;
            }

            filenames.add(patientId+"/"+diseaseId+"/"+file.getSubmittedFileName());
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
