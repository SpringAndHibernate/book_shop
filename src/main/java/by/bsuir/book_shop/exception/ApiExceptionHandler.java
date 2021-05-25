package by.bsuir.book_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Object> handleApiRequestException(BookNotFoundException exception){

        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleApiRequestException(UserNotFoundException exception){

        ApiException apiException = new ApiException(exception.getMessage(),
                HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("UTC")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
