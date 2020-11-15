package edu.arsw.url.persistence.impl;
import edu.arsw.url.model.Url;
import edu.arsw.url.model.User;
import edu.arsw.url.persistence.UrlPersistence;
import edu.arsw.url.repository.UrlRepository;
import edu.arsw.url.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlServiceImpl implements UrlPersistence {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public String createUrl(String apikey, Url url) {
        //verificar usuario
        //contar apikeys
        urlRepository.save(url);
        return url.getHash();
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}