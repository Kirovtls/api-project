package peaksoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peaksoft.dto.response.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerNotFoundException(NotFoundException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName()
        );
    }

    //400
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerBadRequestException(BadRequestException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                e.getClass().getSimpleName()
        );
    }

    //403
    @org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handlerForbiddenException(ForbiddenException e) {
        return new ExceptionResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName()
        );
    }
}