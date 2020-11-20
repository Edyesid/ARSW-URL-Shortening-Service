package edu.arsw.url.model;
import edu.arsw.url.exceptions.UrlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
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
    @Value("${server.port}")
    private int localPort;
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
        /*try {
            LocalDateTime DATE = LocalDateTime.parse(expirationDate);
            this.expirationDate = DATE;
        } catch (Exception e) {
            throw new UrlException(UrlException.INCORRECT_DATE);
        }*/
        this.expirationDate = expirationDate;
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
        System.out.println(localPort);
        url += InetAddress.getLoopbackAddress().getHostName() +  ":" + localPort + "/urls/";
        this.shortUrl = url + urlHash;
    }

    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}