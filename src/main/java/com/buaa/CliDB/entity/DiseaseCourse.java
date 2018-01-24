package com.buaa.CliDB.entity;

 import lombok.Data;
 import org.springframework.data.annotation.Id;
 import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "DiseaseCourse")
public class DiseaseCourse {

    @Id private String id;

    private String diseaseId;

    private String index;

    private DiseaseCourseMainAnswers mainAnswers;

    private DiseaseCourseFirstAnswers firstAnswers;

    private DiseaseCourseThirdAnswers thirdAnswers;

    private DiseaseCourseFourthAnswers fourthAnswers;
}
