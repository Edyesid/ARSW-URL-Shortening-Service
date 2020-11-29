package edu.arsw.url.persistence.cache;

import edu.arsw.url.model.Url;

public interface UrlCache {

    boolean exists(String hash);

    String get(String hash);

    void set(String hash, String url);

    void delete(String hash);

}