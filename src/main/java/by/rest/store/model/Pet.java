package by.rest.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
     
     @NotNull
     @PositiveOrZero
     private long id;
     private Category category;
     private String name;
     private List<String> photoUrls;
     private List<Tag> tags;
     private Status status;
     
     public enum Status {
          available,
          pending,
          sold
     }
     
}
