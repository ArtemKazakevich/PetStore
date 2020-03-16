package by.rest.store;

import by.rest.store.model.Category;
import by.rest.store.model.Pet;
import by.rest.store.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PetServiceTest {
     private PetService petService;
     private Pet pet;
     
     @BeforeEach
     public void createNewPet() {
          petService = new PetService();
          pet = new Pet(
                  0,
                  new Category(0, "test"),
                  "test",
                  new ArrayList<>(),
                  new ArrayList<>(),
                  Pet.Status.available
          );
          petService.addPet(pet);
     }
     
     @Test
     public void getPetsList() {
          Map<Long, Pet> expected = petService.getPetsList();
          
          Map<Long, Pet> actual = new HashMap<>();
          actual.put(pet.getId(), pet);
          
          assertEquals(expected, actual);
     }
     
     @Test
     public void getPet_NO_NULL() {
          Map<Long, Pet> expected = petService.getPetsList();
          assertNotNull(expected);
     }
}
