package edu.arsw.url.services;
import edu.arsw.url.persistence.UrlPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UrlService {
    @Autowired
    UrlPersistence urlpersistence;
    public String createUrl(String originalUrl, String expirationDate) {
        return urlpersistence.createUrl(originalUrl,expirationDate);
    }
}