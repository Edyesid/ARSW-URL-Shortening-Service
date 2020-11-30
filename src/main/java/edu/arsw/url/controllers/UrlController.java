package edu.arsw.url.controllers;
import edu.arsw.url.exceptions.UrlException;
import edu.arsw.url.exceptions.UserException;
import edu.arsw.url.model.Url;
import edu.arsw.url.services.UrlService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
@RestController
@RequestMapping(value = "/urls")
public class UrlController {
    @Autowired
    UrlService urlservice;
    @PostMapping(value = "/create")
    public ResponseEntity<?> createURL(@RequestBody Url url) throws UserException {
        String apikey = url.getApiKey();
        String urlHash = urlservice.createUrl(apikey,url);
        return new ResponseEntity<>(urlHash, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> DeleteURL(@RequestBody String json) throws UrlException {
        try {
            JSONObject objJson= new JSONObject(json);
            String apikey = objJson.getString("apiKey");
            String urlkey = objJson.getString("urlKey");
            return new ResponseEntity<>(urlservice.deleteUrl(apikey,urlkey), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping(value = "/{hash}")
    public ResponseEntity<?> getOriginalUrl(HttpServletResponse response, @PathVariable("hash") String hash) throws UrlException, IOException {
        try {
            response.sendRedirect(urlservice.getOriginalUrl(hash));
            return new ResponseEntity<>(HttpStatus.PERMANENT_REDIRECT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping(value = "/usuarios")
    public ResponseEntity<?> getUsers() throws UnknownHostException {
        return new ResponseEntity<>(urlservice.getUsers(), HttpStatus.ACCEPTED);
    }
}
