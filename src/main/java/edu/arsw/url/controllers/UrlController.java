package edu.arsw.url.controllers;
import edu.arsw.url.model.Url;
import edu.arsw.url.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/urls")
public class UrlController {
    @Autowired
    UrlService urlservice;
    @PostMapping(value = "/create")
    public ResponseEntity<?> createURL(@RequestBody Url url) {
        String apikey = url.getApiKey();
        String urlHash = urlservice.createUrl(apikey,url);
        return new ResponseEntity<>(urlHash, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{apiKey}/{urlKey}/delete")
    public ResponseEntity<?> DeleteURL(@PathVariable("apiKey") String apiKey, @PathVariable("urlKey") String urlKey) {
        String prueba = "pruebaDelete";
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }
    /*--------------------------------------------------------------
    * pruebas
    * --------------------------------------------------------------*/
    @GetMapping(value = "/usuarios")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(urlservice.getUsers(), HttpStatus.ACCEPTED);
    }
}
