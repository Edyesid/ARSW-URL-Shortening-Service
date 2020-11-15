package edu.arsw.url.services;
import edu.arsw.url.model.Url;
import edu.arsw.url.model.User;
import edu.arsw.url.persistence.UrlPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {
    @Autowired
    UrlPersistence urlpersistence;
    public List<User> getUsers() {
        return urlpersistence.getUsers();
    }

    public String createUrl(String apikey, Url url) {
        return urlpersistence.createUrl(apikey,url);
    }
}