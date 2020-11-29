package edu.arsw.url.persistence.impl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.arsw.url.exceptions.UrlException;
import edu.arsw.url.exceptions.UserException;
import edu.arsw.url.model.Url;
import edu.arsw.url.model.User;
import edu.arsw.url.persistence.UrlPersistence;
import edu.arsw.url.persistence.cache.UrlCache;
import edu.arsw.url.repository.UrlRepository;
import edu.arsw.url.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlPersistence {
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Qualifier("UrlCacheImpl")
    UrlCache urlCache;
    private int CASES_NUM = 5;

    private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

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
        String originalUrl = "";
        if(urlCache.exists(hash)) {
            logger.info(String.format("original url in cache"));
            String json= urlCache.get(hash);
            JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
            verifyDateCache(hash, convertedObject);
            originalUrl = convertedObject.get("originalUrl").getAsString();
        } else {
            logger.info(String.format("original url not exist in cache"));
            Url url = urlRepository.findById(hash).orElseThrow(()->new UrlException(UrlException.URL_NOT_FOUND));
            verifyDate(url);
            originalUrl = url.getOriginalUrl();
            urlCache.set(hash,url.toString());
        }
        System.out.println(originalUrl);
        return originalUrl;
    }

    private void verifyDateCache(String hash, JsonObject json) throws UrlException {
        String date = json.get("expirationDate").getAsString();
        LocalDateTime expirationDate = LocalDateTime.parse(date);
        LocalDateTime actualDate = LocalDateTime.now();
        if(actualDate.isAfter(expirationDate)) {
            urlCache.delete(hash);
            deleteUrl(json.get("apiKey").getAsString(),hash);
            throw new UrlException(UrlException.DATE_EXPIRED);
        }
        logger.info(String.format("La fecha de expiración no ha pasado"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void verifyDate(Url url) throws UrlException {
        String date = url.getExpirationDate();
        LocalDateTime expirationDate = LocalDateTime.parse(date);
        LocalDateTime actualDate = LocalDateTime.now();
        if(actualDate.isAfter(expirationDate)) {
            deleteUrl(url.getApiKey(),url.getHash());
            throw new UrlException(UrlException.DATE_EXPIRED);
        }
        logger.info(String.format("La fecha de expiracion no ha pasado"));
    }
}