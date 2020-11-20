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
        return MongoClients.create("mongodb+srv://edwin_arsw:edwin123@cluster0.s2ygl.mongodb.net/UrlShortingDB?retryWrites=true&w=majority");
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "UrlShortingDB");
    }
}
