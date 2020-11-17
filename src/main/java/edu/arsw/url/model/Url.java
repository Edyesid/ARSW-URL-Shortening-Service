package edu.arsw.url.model;
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
    private String originalUrl;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private String apiKey;
    public Url(String originalUrl, String expirationDate, String apiKey) throws UrlException {
        setHash();
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
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) throws UrlException {
        try {
            LocalDateTime DATE = LocalDateTime.parse(expirationDate);
            this.expirationDate = DATE;
        } catch (Exception e) {
            throw new UrlException(UrlException.INCORRECT_DATE);
        }
    }
    public String getHash() {
        return hash;
    }
    public void setHash() {
        String url = "http//:urlShort/";
        String SIMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz0123456789@#$%&=?¿";
        for (int i = 0; i < 6; i++) {
            url += SIMBOLS.charAt((int)Math.floor(Math.random()*SIMBOLS.length()));
        }
        this.hash = url;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}