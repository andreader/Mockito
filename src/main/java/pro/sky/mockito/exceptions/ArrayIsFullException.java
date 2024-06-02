package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;

public class ArrayIsFullException extends ResponseException {
    public ArrayIsFullException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}