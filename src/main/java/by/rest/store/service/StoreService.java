package by.rest.store.service;

import by.rest.store.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StoreService {
     
     @Autowired
     private Map<Long, Order> orders;
     
     public List<Order> inventoryStore(Order.Status status) {
          List<Order> foundStore = new ArrayList<>();
          for (Order pet: orders.values()) {
               if (pet.getStatus().equals(status)) {
                    foundStore.add(pet);
               }
          }
          return foundStore;
     }
}
