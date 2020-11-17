package edu.arsw.url.exceptions;

public class UrlException extends Exception {
    public static final String INCORRECT_URL = "La url ingresada es incorrecta.";
    public static final String INCORRECT_DATE = "La fecha ingresada es incorrecta";
    public static final String URL_NOT_FOUND = "La url no existe.";
    public UrlException() {
        super();
    }
    public UrlException(String message) {
        super(message);
    }
}
