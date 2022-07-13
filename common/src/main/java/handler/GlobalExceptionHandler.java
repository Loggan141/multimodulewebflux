package handler;


import exception.BadRequestException;
import exception.TruckNotFoundMonoResponse;
import exception.contracterror.ErrorObject;
import exception.contracterror.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


import reactor.core.publisher.Mono;

import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(code = NOT_FOUND)
    public Mono<ErrorResponse> handlerNotFoundException(ClassNotFoundException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_FOUND.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

    @ExceptionHandler(TruckNotFoundMonoResponse.class)
    @ResponseStatus(code = NOT_FOUND)
    public Mono<ErrorResponse> handlerNotFoundException(TruckNotFoundMonoResponse error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_FOUND.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(code = NOT_FOUND)
    public Mono<ErrorResponse> handlerResponseStatusException(ResponseStatusException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_FOUND.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = METHOD_NOT_ALLOWED)
    public Mono<ErrorResponse> handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(METHOD_NOT_ALLOWED.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = BAD_REQUEST)
    public Mono<ErrorResponse> handlerBadRequestException(BadRequestException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(BAD_REQUEST.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = METHOD_NOT_ALLOWED)
    public Mono<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        //.message(METHOD_NOT_ALLOWED.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }


//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ExceptionDetails> handleNullPointerException(
//            NullPointerException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Bad Request : Null")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(BAD_REQUEST.value())
//                .title("Cannot be Null")
//                .developerMessage(exception.getClass().getName())
//                .build(), BAD_REQUEST);
//    }

//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Bad Request : Argument Not Valid")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(BAD_REQUEST.value())
//                .title("Not valid argument")
//                .developerMessage(exception.getClass().getName())
//                .build(), BAD_REQUEST);
//    }
    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(code = NOT_ACCEPTABLE)
    public Mono<ErrorResponse> handleUnexpectedTypeException(UnexpectedTypeException error) {
        return Mono.just(ErrorResponse.builder()
                .timestamp(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
                .error(List.of(ErrorObject.builder()
                        .message(NOT_ACCEPTABLE.name())
                        .field(error.getMessage())
                        .parameter(error.getClass().getTypeName())
                        .build()))
                .build());
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ExceptionDetails> handleIllegalArgumentException(
//            IllegalArgumentException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Bad Request : Invalid Argument")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.NOT_ACCEPTABLE.value())
//                .title("Invalid Argument")
//                .developerMessage(exception.getClass().getName())
//                .build(), HttpStatus.NOT_ACCEPTABLE);
//    }
//
//    @ExceptionHandler(HttpMessageConversionException.class)
//    public ResponseEntity<ExceptionDetails> handleHttpMessageConversionException(
//            HttpMessageConversionException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Internal Server Error")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .title("Something in server is wrong")
//                .developerMessage(exception.getClass().getName())
//                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    @ExceptionHandler(ResourceAccessException.class)
//    public ResponseEntity<ExceptionDetails> handleResourceAccessException(
//            ResourceAccessException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Internal Server Error")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .title(" ")
//                .developerMessage(exception.getClass().getName())
//                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    @ExceptionHandler(DecodingException.class)
//    public ResponseEntity<ExceptionDetails> handleDecodingException(
//            DecodingException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Check your body request")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(BAD_REQUEST.value())
//                .title(" ")
//                .developerMessage(exception.getClass().getName())
//                .build(), BAD_REQUEST);
//    }
//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<ExceptionDetails> handleResponseStatusException(
//            ResponseStatusException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Not Found")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(NOT_FOUND.value())
//                .developerMessage(exception.getClass().getName())
//                .build(), NOT_FOUND);
//    }
//    @ExceptionHandler(ServerWebInputException.class)
//    public ResponseEntity<ExceptionDetails> handleServerWebInputException(
//            ServerWebInputException exception){
//        return new ResponseEntity<>(ExceptionDetails.builder()
//                .title("Bad Request")
//                .detail(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .status(BAD_REQUEST.value())
//                .developerMessage(exception.getClass().getName())
//                .build(), BAD_REQUEST);
//    }

}
