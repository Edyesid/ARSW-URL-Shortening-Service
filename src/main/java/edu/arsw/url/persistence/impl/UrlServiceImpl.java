package edu.arsw.url.persistence.impl;
import edu.arsw.url.exceptions.UrlException;
import edu.arsw.url.exceptions.UserException;
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
    private int CASES_NUM = 2;
    @Override
    public String createUrl(String apikey, Url url) throws UserException {
        //contar apikeys
        if(urlRepository.countByApiKey(apikey) >= CASES_NUM) {
            throw new UserException(UserException.MAX_ALLOWED);
        }
        urlRepository.save(url);
        return url.getShortUrl();
    }
    @Override
    public String deleteUrl(String apikey, String urlkey) throws UrlException {
        String message = "URL Removed";
        if(!urlRepository.existsById(urlkey)) {
            throw new UrlException(UrlException.URL_NOT_FOUND);
        }
        urlRepository.deleteById(urlkey);
        return message;
    }

    @Override
    public String getOriginalUrl(String hash) throws UrlException {
        Url url = urlRepository.findById(hash).orElseThrow(()->new UrlException(UrlException.URL_NOT_FOUND));
        System.out.println(url.getOriginalUrl());
        return url.getOriginalUrl();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}