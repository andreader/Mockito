package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArrayIsFullException extends ResponseException {
    public ArrayIsFullException(String message, HttpStatus status) {
        super(message, status);
    }

}