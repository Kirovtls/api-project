package peaksoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.dto.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ExceptionResponse exceptionResponse(NotFoundException e) {
        return new ExceptionResponse(e.getMessage(),
                HttpStatus.NOT_FOUND, e.getClass().getSimpleName());

    }
}