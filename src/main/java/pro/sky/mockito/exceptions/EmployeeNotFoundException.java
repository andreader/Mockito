package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends ResponseException {

    public EmployeeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}