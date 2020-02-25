package by.rest.store.controller;

import by.rest.store.exception.store.StoreBadRequestException;
import by.rest.store.exception.store.StoreNotFoundException;
import by.rest.store.model.Order;
import by.rest.store.model.request.store.StoreRequest;
import by.rest.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
     private StoreService storeService;
     
     @Autowired
     private Map<Long, String> tokens;
     
     @Autowired
     private Map<Long, Order> orders;
     
     public StoreController(StoreService storeService) {
          this.storeService = storeService;
     }
     
     @GetMapping(path = "/order/{orderID}")
     public ResponseEntity<Order> getOrder(@PathVariable Long orderID,
                                           @RequestBody StoreRequest storeRequest) {
          if (!tokens.containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!orders.containsKey(orderID)) throw new StoreNotFoundException();
          return new ResponseEntity<>(orders.get(orderID), HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/order/{orderID}")
     public ResponseEntity<String> deleteOrder(@PathVariable Long orderID,
                                               @RequestBody StoreRequest storeRequest) {
          if (!tokens.containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          if (!orders.containsKey(orderID)) throw new StoreNotFoundException();
          orders.remove(orderID);
          return new ResponseEntity<>("The order was successfully deleted", HttpStatus.OK);
     }
     
     @PostMapping(path = "/order")
     public ResponseEntity<String> addOrder(@Valid @RequestBody StoreRequest storeRequest) {
          if (!tokens.containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          orders.put(storeRequest.getOrder().getId(), storeRequest.getOrder());
          return new ResponseEntity<>("The order was added successfully", HttpStatus.OK);
     }
     
     @GetMapping("/inventory")
     public ResponseEntity<List<Order>> inventory(@RequestBody StoreRequest storeRequest) {
          if (!tokens.containsValue(storeRequest.getToken())) throw new StoreBadRequestException();
          return new ResponseEntity<>(storeService.inventoryStore(storeRequest.getStatus()), HttpStatus.OK);
     }
}
