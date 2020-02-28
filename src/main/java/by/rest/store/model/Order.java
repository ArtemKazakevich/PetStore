package by.rest.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
     
     @NotNull
     @PositiveOrZero
     private long id;
     
     @NotNull
     @PositiveOrZero
     private long petId;
     private int quantity;
     private String shipDate;
     private Status status;
     private boolean complete;
     
     public enum Status {
          placed,
          approved,
          delivered
     }
}
