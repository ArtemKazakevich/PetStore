package by.rest.store.service;

import by.rest.store.model.Pet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PetService {
     
     private final Map<Long, Pet> petsList;
     
     public PetService() {
          petsList = new HashMap<>();
     }
     
     public PetService(Map<Long, Pet> petsList) {
          this.petsList = petsList;
     }
     
     public Map<Long, Pet> getPetsList() {
          return petsList;
     }
     
     public void petUpdateById(Long id, String name, Pet.Status status) {
          petsList.get(id).setName(name);
          petsList.get(id).setStatus(status);
     }
     
     public void addPet(Pet pet) {
          petsList.put(pet.getId(), pet);
     }
     
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
