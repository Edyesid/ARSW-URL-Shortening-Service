package edu.arsw.url.model;
import com.google.gson.Gson;
import edu.arsw.url.exceptions.UrlException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Document(collection = "Url")
public class Url {
    @Id
    @NotNull
    private String hash;
    private String shortUrl;
    private String originalUrl;
    private String creationDate;
    private String expirationDate;
    private String apiKey;
    public Url(String originalUrl, String expirationDate, String apiKey) throws UrlException {
        setHash();
        setShortUrl(this.hash);
        setOriginalUrl(originalUrl);
        setCreationDate(LocalDateTime.now());
        setExpirationDate(expirationDate);
        this.apiKey = apiKey;
    }
    public String getOriginalUrl() {
        return originalUrl;
    }
    public void setOriginalUrl(String originalUrl) throws UrlException {
        String pattern = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        if (originalUrl.matches(pattern)) {
            this.originalUrl = originalUrl;
        } else {
            throw new UrlException(UrlException.INCORRECT_URL);
        }
    }
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate.toString();
    }
    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) throws UrlException {
        String pattern = "^(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})";
        if (expirationDate.matches(pattern)) {
            this.expirationDate = expirationDate;
        } else {
            throw new UrlException(UrlException.INCORRECT_DATE);
        }
    }
    public String getHash() {
        return hash;
    }
    public void setHash() {
        String url = "";
        String SIMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz0123456789";
        for (int i = 0; i < 6; i++) {
            url += SIMBOLS.charAt((int)Math.floor(Math.random()*SIMBOLS.length()));
        }
        this.hash = url;
    }
    public String getShortUrl() {
        return shortUrl;
    }
    public void setShortUrl(String urlHash) {
        String url = "http://";
        url += System.getenv("HOST_NAME") + "/urls/";
        this.shortUrl = url + urlHash;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}