package com.buaa.CliDB.config;

import com.google.common.collect.Lists;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.util.List;

@Configuration
@PropertySource("classpath:/mongodb-configuration.properties")
@EnableMongoRepositories(basePackages = {"com.buaa.CliDB.repository"})
@EnableMongoAuditing
public class MongoDBConfiguration extends AbstractMongoConfiguration {

    @Autowired Environment environment;

//    @Bean
//    public MongoClientOptions mongoClientOptions() {
//        return MongoClientOptions.builder()
//                .connectionsPerHost(environment.getProperty("mongodb.options.conn_per_host",Integer.class))
//                .connectTimeout(environment.getProperty("mongodb.options.conn_timeout", Integer.class))
//                .maxWaitTime(environment.getProperty("mongodb.options.conn_max_waittime",Integer.class))
//                //.socketKeepAlive(true)
//                .build();
//    }
//
//    private @Autowired MongoClientOptions mongoClientOptions;
//
//    @Bean
//    public MongoCredential mongoCredential() {
//        return MongoCredential.createCredential(environment.getProperty("mongodb.user.name"),
//                getDatabaseName(),
//                environment.getProperty("mongodb.user.password").toCharArray());
//    }

//    public MongoClient mongoClient() {
//        String[] hosts = environment.getProperty("mongodb.hosts").split(",");
//        String[] ports = environment.getProperty("mongodb.ports").split(",");
//
//        assert(hosts.length==ports.length);
//
//        List<ServerAddress> serverAddressList = Lists.newArrayList();
//
//        for ( int i = 0; i < hosts.length; ++ i) {
//            try {
//                serverAddressList.add(new ServerAddress(hosts[i], Integer.parseInt(ports[i])));
//            } catch (IOException e) {
//                continue;
//            }
//        }
//        return new MongoClient(serverAddressList,mongoClientOptions);
//    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo();
    }
//
//    @Override
//    public MongoTemplate mongoTemplate() {
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), getDatabaseName());
//        mongoTemplate.setWriteResultChecking(WriteResultChecking.EXCEPTION);
//        return mongoTemplate;
//    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty("mongodb.dbname");
    }
}
