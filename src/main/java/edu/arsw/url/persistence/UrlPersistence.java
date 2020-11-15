package edu.arsw.url.persistence;

import edu.arsw.url.model.Url;
import edu.arsw.url.model.User;

import java.util.List;

public interface UrlPersistence {
    public List<User> getUsers();
    public String createUrl(String apikey, Url url);
}