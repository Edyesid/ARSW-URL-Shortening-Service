package edu.arsw.url.persistence;
public interface UrlPersistence {
    public String createUrl(String originalUrl, String expirationDate);
}