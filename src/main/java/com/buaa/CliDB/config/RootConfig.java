package com.buaa.CliDB.config;



import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.buaa.CliDB.service", "com.buaa.CliDB.logic", "com.buaa.CliDB.exception"},
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value= EnableWebMvc.class)
})
@Import(value = {MongoDBConfiguration.class})
public class RootConfig { }
