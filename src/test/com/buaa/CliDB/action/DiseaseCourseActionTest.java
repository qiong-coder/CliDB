package com.buaa.CliDB.action;

import com.buaa.CliDB.config.RootConfig;
import com.buaa.CliDB.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration
public class DiseaseCourseActionTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void post() throws Exception
    {
        MockMultipartFile diseaseCourse = new MockMultipartFile("diseaseCourse", "", MediaType.APPLICATION_JSON_UTF8_VALUE, "{\"patientId\":1}".getBytes());
        MockPart test1 = new MockPart("images", "test.jpg", "test".getBytes());
        MockPart test2 = new MockPart("images", "test2.jpg", "test2".getBytes());


//        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/disease_course/1/")
//                .file(diseaseCourse).merge()
//                .part(test1)
//                .part(test2))
//        .andExpect(status().isOk());

    }

}
