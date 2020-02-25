package by.rest.store.controller;

import by.rest.store.exception.pet.PetBadRequestException;
import by.rest.store.exception.pet.PetNotFoundException;
import by.rest.store.model.Pet;
import by.rest.store.model.request.pet.PetRequest;
import by.rest.store.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = ("/pet"))
public class PetController {
     private PetService petService;
     
     @Autowired
     private Map<Long, String> tokens;
     
     @Autowired
     private Map<Long, Pet> petsList;
     
     public PetController(PetService petService) {
          this.petService = petService;
     }
     
     @GetMapping(path = "/{petID}")
     public ResponseEntity<Pet> getPet(@PathVariable Long petID,
                                                  @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petsList.containsKey(petID)) throw new PetNotFoundException();
          return new ResponseEntity<>( petsList.get(petID),HttpStatus.OK);
     }
     
     @PostMapping(path = "/{petID}")
     public ResponseEntity<String> updatedPet(@PathVariable Long petID,
                                              @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petsList.containsKey(petID)) throw new PetNotFoundException();
          petsList.get(petID).setName(petRequest.getName());
          petsList.get(petID).setStatus(petRequest.getStatus());
          return new ResponseEntity<>("The update was successful", HttpStatus.OK);
     }
     
     @DeleteMapping(path = "/{petID}")
     public ResponseEntity<String> deletePet(@PathVariable Long petID,
                                             @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petsList.containsKey(petID)) throw new PetNotFoundException();
          petsList.remove(petID);
          return new ResponseEntity<>("The deletion was successful", HttpStatus.OK);
     }
     
     @PostMapping(path = "/{petId}/uploadImage")
     public ResponseEntity<String> uploadImagePet(@PathVariable Long petID,
                                                  @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petsList.containsKey(petID)) throw new PetNotFoundException();
          petsList.get(petID).getPhotoUrls().add(petRequest.getPetPhotoUrl());
          return new ResponseEntity<>("The image is successfully loaded", HttpStatus.OK);
     }
     
     @PostMapping
     public ResponseEntity<String> addPet(@Valid @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          petsList.put(petRequest.getPet().getId(), petRequest.getPet());
          return new ResponseEntity<>("Pet added successfully", HttpStatus.OK);
     }
     
     @PutMapping
     public ResponseEntity<String> updatePet(@Valid @RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          if (!petService.updatePet(petRequest.getPet())) throw new PetNotFoundException();
          return new ResponseEntity<>("The update was successful", HttpStatus.OK);
     }
     
     @GetMapping(path = "/findByStatus")
     public ResponseEntity<List<Pet>> findByStatus(@RequestBody PetRequest petRequest) {
          if (!tokens.containsValue(petRequest.getToken())) throw new PetBadRequestException();
          return new ResponseEntity<>(petService.findByStatus(petRequest.getStatus()), HttpStatus.OK);
     }
     
}
