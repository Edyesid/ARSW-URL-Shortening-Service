package edu.arsw.url.persistence.cache;

public interface UrlCache {
    boolean exists(String city);

    String get(String city);

    void set(String city, String payload);

    void set(String city, String payload, long TTL);
}
