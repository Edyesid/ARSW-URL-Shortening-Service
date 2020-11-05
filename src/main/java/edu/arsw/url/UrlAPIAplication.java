package edu.arsw.url;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * @author Edwin Yesid Rodriguez Maldonado
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.arsw.url"})
public class UrlAPIAplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlAPIAplication.class, args);
    }
}