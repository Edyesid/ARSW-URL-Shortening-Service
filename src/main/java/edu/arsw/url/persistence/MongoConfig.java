package edu.arsw.url.persistence;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import edu.arsw.url.UrlAPIAplication;
import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String uri;
    @Value("${spring.data.mongodb.database}")
    private String dbname;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(uri);
    }
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), dbname);
    }

}