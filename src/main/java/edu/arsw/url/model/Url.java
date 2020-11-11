package edu.arsw.url.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
@Document(collection = "urls")
public class Url {
    @Id
    @NotNull
    private String hash;
    private String originalUrl;
    private String creationDate;
    private String expirationDate;
    private String userId;

    public Url(String originalUrl, String expirationDate) {
        setHash();
        this.originalUrl = originalUrl;
        setCreationDate();
        this.expirationDate = expirationDate;
        this.userId = "0";
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        Date date = new Date();
        String actualDate = date.toString();
        this.creationDate = actualDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash() {
        String url = "Http//:urlShort/";
        String urlHash = UUID.randomUUID().toString();
        this.hash = url + urlHash;
    }
}