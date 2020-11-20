package edu.arsw.url;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author Edwin Yesid Rodriguez Maldonado
 */
@SpringBootApplication
public class UrlAPIAplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlAPIAplication.class, args);
    }
}