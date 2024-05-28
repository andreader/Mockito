package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends ResponseException {

    public EmployeeAlreadyAddedException(String message, HttpStatus status) {
        super(message, status);
    }

}
