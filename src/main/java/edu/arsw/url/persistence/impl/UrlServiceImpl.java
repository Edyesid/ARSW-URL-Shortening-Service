package edu.arsw.url.persistence.impl;
import edu.arsw.url.model.Url;
import edu.arsw.url.persistence.UrlPersistence;
import edu.arsw.url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UrlServiceImpl implements UrlPersistence {
    @Autowired
    UrlRepository urlRepository;

    @Override
    public String createUrl(String originalUrl, String expirationDate) {
        Url url = new Url(originalUrl,expirationDate);
        urlRepository.save(url);
        return url.getHash();
    }
}