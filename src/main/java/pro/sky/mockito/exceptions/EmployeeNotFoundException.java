package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends ResponseException {

    public EmployeeNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

}