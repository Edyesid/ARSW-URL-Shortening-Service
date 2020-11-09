package edu.arsw.url.repository;
import edu.arsw.url.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<User, Integer> {
}