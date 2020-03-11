package by.rest.store.controller.exceptionController;

import by.rest.store.exception.pet.PetBadRequestException;
import by.rest.store.exception.pet.PetMethodNotAllowedException;
import by.rest.store.exception.pet.PetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class PetExceptionController extends ResponseEntityExceptionHandler {
     
     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
          return new ResponseEntity<>("Pet not valid", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(value = {ConstraintViolationException.class})
     protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
          return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
     }

     @ExceptionHandler(PetBadRequestException.class)
     public ResponseEntity exBadRequest() {
          log.warn("Bad Request");
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(PetNotFoundException.class)
     public ResponseEntity exNotFound() {
          log.warn("Not Found");
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
     
     @ExceptionHandler(PetMethodNotAllowedException.class)
     public ResponseEntity exMethodNotAllowed() {
          log.warn("Method Not Allowed");
          return new ResponseEntity("Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
     }
}
