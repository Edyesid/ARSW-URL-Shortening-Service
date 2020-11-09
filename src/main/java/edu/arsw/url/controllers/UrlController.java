package edu.arsw.url.controllers;
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
    @PostMapping(value = "/{apikey}/{originalUrl}/{expireDate}/create")
    public ResponseEntity<?> createURL(@PathVariable("apikey") String apikey,@PathVariable("originalUrl") String originalUrl, @PathVariable("expireDate") String expireDate) {
        String urlHash = urlservice.createUrl(originalUrl,expireDate);
        return new ResponseEntity<>(urlHash, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{apiKey}/{urlKey}/delete")
    public ResponseEntity<?> DeleteURL(@PathVariable("apiKey") String apiKey, @PathVariable("urlKey") String urlKey) {
        String prueba = "pruebaDelete";
        return new ResponseEntity<>(prueba, HttpStatus.OK);
    }
}
