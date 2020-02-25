package by.rest.store.controller.exceptionController;

import by.rest.store.exception.store.StoreBadRequestException;
import by.rest.store.exception.store.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreExceptionController {
     
     @ExceptionHandler(StoreBadRequestException.class)
     public ResponseEntity exBadRequest() {
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(StoreNotFoundException.class)
     public ResponseEntity exNotFound() {
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
}
