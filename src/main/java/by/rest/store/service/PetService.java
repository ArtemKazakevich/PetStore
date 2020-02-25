package by.rest.store.service;

import by.rest.store.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PetService {
     
     @Autowired
     private Map<Long, Pet> petsList;
     
     public boolean updatePet(Pet pet) {
          if (!petsList.containsKey(pet.getId())) return false;
          petsList.put(pet.getId(), pet);
          return true;
     }
     
     public List<Pet> findByStatus(Pet.Status status) {
          List<Pet> pets = new ArrayList<>();
          for (Pet pet: petsList.values()) {
               if (pet.getStatus().equals(status)) {
                    pets.add(pet);
               }
          }
          return pets;
     }
}
