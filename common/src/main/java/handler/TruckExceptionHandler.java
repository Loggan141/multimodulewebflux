package handler;

import exception.ExceptionDetails;
import exception.TruckNotFoundMonoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice

public class TruckExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TruckNotFoundMonoResponse.class)
    public ResponseEntity<ExceptionDetails> handleTruckNotFound(TruckNotFoundMonoResponse e){
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request : NOT FOUND")
                .detail(e.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Truck not found")
                .developerMessage(e.getClass().getName())
                .build(), HttpStatus.NOT_FOUND);
    }

}
