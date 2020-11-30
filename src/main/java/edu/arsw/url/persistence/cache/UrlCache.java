package edu.arsw.url.persistence.cache;
public interface UrlCache {

    boolean exists(String hash);

    String get(String hash);

    void set(String hash, String url);

    void delete(String hash);

}