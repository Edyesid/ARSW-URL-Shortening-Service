package edu.arsw.url.exceptions;
public class UserException extends Exception{
    public static final String MAX_ALLOWED  = "Maximas urls por usuario permitidas";
    public static final String USER_NOT_EXIST = "El apikey de usuario no existe";
    public UserException() {
        super();
    }
    public UserException(String message) {
        super(message);
    }
}
