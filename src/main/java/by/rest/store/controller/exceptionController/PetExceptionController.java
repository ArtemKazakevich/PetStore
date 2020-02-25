package by.rest.store.controller.exceptionController;

import by.rest.store.exception.pet.PetBadRequestException;
import by.rest.store.exception.pet.PetMethodNotAllowedException;
import by.rest.store.exception.pet.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionController {

     @ExceptionHandler(PetBadRequestException.class)
     public ResponseEntity exBadRequest() {
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(PetNotFoundException.class)
     public ResponseEntity exNotFound() {
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
     
     @ExceptionHandler(PetMethodNotAllowedException.class)
     public ResponseEntity exMethodNotAllowed() {
          return new ResponseEntity("Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED);
     }
}
