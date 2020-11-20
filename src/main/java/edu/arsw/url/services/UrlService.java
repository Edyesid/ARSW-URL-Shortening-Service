package edu.arsw.url.services;
import edu.arsw.url.exceptions.UrlException;
import edu.arsw.url.exceptions.UserException;
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

    public String createUrl(String apikey, Url url) throws UserException {
        return urlpersistence.createUrl(apikey,url);
    }
    public String deleteUrl(String apikey, String urlkey) throws UrlException {
        return urlpersistence.deleteUrl(apikey,urlkey);
    }
    public String getOriginalUrl(String hash) throws UrlException {
        return urlpersistence.getOriginalUrl(hash);
    }
}