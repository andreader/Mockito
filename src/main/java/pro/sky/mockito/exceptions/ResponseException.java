package pro.sky.mockito.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    public ResponseException(String message, HttpStatus status) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}