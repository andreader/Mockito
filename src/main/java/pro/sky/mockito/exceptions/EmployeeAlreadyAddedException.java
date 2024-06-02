package pro.sky.mockito.exceptions;
import org.springframework.http.HttpStatus;

public class EmployeeAlreadyAddedException extends ResponseException {

    public EmployeeAlreadyAddedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

}
