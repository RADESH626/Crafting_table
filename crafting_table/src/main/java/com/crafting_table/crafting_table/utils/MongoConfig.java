package com.crafting_table.crafting_table.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    private final String connectionString = "mongodb+srv://Emanuel:Emanuelgalvis123@clusteremanuelgalvis.djph7.mongodb.net/Crafting_table?retryWrites=true&w=majority&appName=ClusterEmanuelGalvis";

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connString = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .build();
        return MongoClients.create(settings);
    }
}
