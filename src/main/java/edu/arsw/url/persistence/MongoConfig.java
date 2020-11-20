package edu.arsw.url.persistence;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
@Configuration
public class MongoConfig {
    /*
    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String dbname;*/
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://edwin_arsw:edwin123@cluster0-shard-00-00.s2ygl.mongodb.net:27017,cluster0-shard-00-01.s2ygl.mongodb.net:27017,cluster0-shard-00-02.s2ygl.mongodb.net:27017/<dbname>?ssl=true&replicaSet=atlas-crmszv-shard-0&authSource=admin&retryWrites=true&w=majority");
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "UrlShortingDB");
    }
}
