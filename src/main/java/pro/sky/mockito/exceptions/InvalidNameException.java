package pro.sky.mockito.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidNameException extends ResponseException {

    public InvalidNameException(String message, HttpStatus status) {
        super(message, status);
    }
}