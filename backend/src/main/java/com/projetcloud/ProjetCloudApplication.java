package com.projetcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProjetCloudApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjetCloudApplication.class, args);

    }
    @Bean
    public MongoTemplate mongoTemplate() {
        // Vous pouvez personnaliser davantage la configuration ici si nécessaire
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb+srv://projetCloud:qopjor-bygcut-5guvnY@projetcloud.kyhrlvv.mongodb.net/dbProjetCloud?retryWrites=true&w=majority"));
    }
}
