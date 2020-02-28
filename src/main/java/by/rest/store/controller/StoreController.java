package by.rest.store.controller;

import by.rest.store.exception.store.StoreBadRequestException;
import by.rest.store.exception.store.StoreNotFoundException;
import by.rest.store.model.Order;
import by.rest.store.model.request.store.StoreRequest;
import by.rest.store.service.StoreService;
import by.rest.store.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
     private StoreService storeService;
     private UserService userService;
     
     public StoreController(StoreService storeService, UserService userService) {
          this.storeService = storeService;
          this.userService = userService;
     }
     
     @GetMapping(path = "/order/{orderID}")
     public ResponseEntity<Order> getOrder(@PathVariable Long orderID,
                                           @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!storeService.getOrdersMap().containsKey(orderID)) throw new StoreNotFoundException();
          return new ResponseEntity<>(storeService.getOrdersMap().get(orderID), HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/order/{orderID}")
     public ResponseEntity<String> deleteOrder(@PathVariable Long orderID,
                                               @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!storeService.getOrdersMap().containsKey(orderID)) throw new StoreNotFoundException();
          storeService.deleteOrder(orderID);
          return new ResponseEntity<>("The order was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/order")
     public ResponseEntity<String> addOrder(@Valid @RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          storeService.addOrder(storeRequest.getOrder());
          return new ResponseEntity<>("The order was added successfully", HttpStatus.OK);
     }
     
     @GetMapping("/inventory")
     public ResponseEntity<List<Order>> inventory(@RequestBody StoreRequest storeRequest) {
          if (!userService.getTokens().containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          return new ResponseEntity<>(storeService.inventoryStore(storeRequest.getStatus()), HttpStatus.OK);
     }
}
