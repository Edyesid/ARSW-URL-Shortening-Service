package edu.arsw.url.repository;
import edu.arsw.url.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UrlRepository extends MongoRepository<Url, String> {
    Long countByApiKey(String apiKey);
}

