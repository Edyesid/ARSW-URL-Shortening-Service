package edu.arsw.url.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
@Document(collection = "Url")
public class Url {
    @Id
    @NotNull
    private String hash;
    private String originalUrl;
    private LocalDateTime creationDate;
    private String expirationDate;
    private String apiKey;
    public Url(String originalUrl, String expirationDate, String apiKey) {
        setHash();
        this.originalUrl = originalUrl;
        setCreationDate(LocalDateTime.now());
        this.expirationDate = expirationDate;
        this.apiKey = apiKey;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash() {
        String url = "http//:urlShort/";
        String urlHash = UUID.randomUUID().toString();
        this.hash = url + urlHash;
    }
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}