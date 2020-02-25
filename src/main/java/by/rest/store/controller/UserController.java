package by.rest.store.controller;

import by.rest.store.exception.user.UserBadRequestException;
import by.rest.store.exception.user.UserNotFoundException;
import by.rest.store.model.User;
import by.rest.store.model.request.user.UserRequest;
import by.rest.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {
     
     private UserService userService;
     
     @Autowired
     private Map<Long, String> tokens;
     
     @Autowired
     private Map<String, User> usersList;
     
     public UserController(UserService userService) {
          this.userService = userService;
     }
     
     @GetMapping(path = "/{userName}")
     public ResponseEntity<User> getUser(@PathVariable String userName,
                                         @RequestBody UserRequest userRequest) {
          if (!tokens.containsValue(userRequest.getToken())) throw new UserBadRequestException();
          if (!usersList.containsKey(userName)) throw new UserNotFoundException();
          return new ResponseEntity<>(usersList.get(userName), HttpStatus.OK);
     }
     
     @PutMapping(path = "/{userName}")
     public ResponseEntity<String> updateUser(@PathVariable String userName,
                                              @RequestBody UserRequest userRequest) {
          if (!tokens.containsValue(userRequest.getToken())) throw new UserBadRequestException();
          if (!usersList.containsKey(userName)) throw new UserNotFoundException();
          usersList.put(userName, userRequest.getUser());
          return new ResponseEntity<>("The user is successfully updated", HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/{username}")
     public ResponseEntity<String> deleteUser(@PathVariable String userName,
                                              @RequestBody UserRequest userRequest) {
          if (!tokens.containsValue(userRequest.getToken())) throw new UserBadRequestException();
          if (!usersList.containsKey(userName)) throw new UserNotFoundException();
          usersList.remove(userName);
          return new ResponseEntity<>("The user was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/login")
     public ResponseEntity<String> loginUser(@RequestBody UserRequest userRequest) {
          String token = userService.authentication(userRequest.getUser());
          if (token == null) throw new UserBadRequestException();
          return new ResponseEntity<>("log in successfully", HttpStatus.OK);
     }
     
     @PostMapping(path = "/logout")
     public ResponseEntity<String> logoutUser(@RequestBody UserRequest userRequest) {
          if (!tokens.containsValue(userRequest.getToken())) throw new UserBadRequestException();
          tokens.remove(userRequest.getToken());
          return new ResponseEntity<>("Exit successfully completed", HttpStatus.OK);
     }
     
     @PostMapping
     public ResponseEntity<String> createUser(@Valid @RequestBody UserRequest userRequest) {
          usersList.put(userRequest.getUser().getUserName(), userRequest.getUser());
          return new ResponseEntity<>("The user is created", HttpStatus.OK);
     }
     
     @PostMapping(path = "/createWithList")
     public ResponseEntity<String> createWithListUser(@Valid @RequestBody List<User> users) {
          userService.addAllUsers(users);
          return new ResponseEntity<>("User output completed", HttpStatus.OK);
     }
}
