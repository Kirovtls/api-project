package peaksoft.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResponse {
    private String message;

    private HttpStatus httpStatus;
    private String exceptionClassName;

    public ExceptionResponse(String message, HttpStatus httpStatus, String exceptionClassName) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.exceptionClassName = exceptionClassName;
    }
}