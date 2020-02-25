package by.rest.store.controller.exceptionController;

import by.rest.store.exception.user.UserBadRequestException;
import by.rest.store.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
     
     @ExceptionHandler(UserBadRequestException.class)
     public ResponseEntity exBadRequest() {
          return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
     }
     
     @ExceptionHandler(UserNotFoundException.class)
     public ResponseEntity exNotFound() {
          return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
     }
}
