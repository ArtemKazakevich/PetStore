package by.rest.store.service;

import by.rest.store.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StoreService {
     
     private final Map<Long, Order> orders;
     
     public StoreService(Map<Long, Order> orders) {
          this.orders = orders;
     }
     
     public Map<Long, Order> getOrdersMap() {
          return orders;
     }
     
     public void addOrder(Order order) {
          orders.put(order.getId(), order);
     }
     
     public void deleteOrder(Long id) {
          orders.remove(id);
     }
     
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
