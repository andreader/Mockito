package pro.sky.mockito.handler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.mockito.model.ErrorDTO;
import pro.sky.mockito.exceptions.ResponseException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorDTO> handleException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        errorDTO.setStatus(String.valueOf(((ResponseException) ex).getStatus().value()));
        errorDTO.setTime(new Date().toString());
        return ResponseEntity.status(((ResponseException) ex).getStatus()).body(errorDTO);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorDTO> handleArrayIsFullException(ResponseException ex) {
        return handleException(ex);
    }
}