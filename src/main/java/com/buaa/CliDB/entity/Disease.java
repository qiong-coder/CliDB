package com.buaa.CliDB.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Disease")
public class Disease {

    @Id private String id;

    private String patientId;

    private String mainDisease;

    private String mainSuit;

    private String past;

    private String type;

    private String typeChild;


}
