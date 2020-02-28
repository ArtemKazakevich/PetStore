//package by.rest.store;
//
//import by.rest.store.model.Category;
//import by.rest.store.model.Pet;
//import by.rest.store.model.Tag;
//import by.rest.store.service.PetService;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.junit.jupiter.api.Assertions;
//import sun.jvm.hotspot.utilities.Assert;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//public class PetServiceTest {
//
//     private Map<Long, Pet> petsMap;
//     private PetService petService;
//
//     @Before
//     public Pet createNewPet() {
//          Pet pet = new Pet();
//          pet.setId(123456);
//          pet.setCategory(new Category(1, "name"));
//          pet.setName("test");
//          pet.setPhotoUrls(new ArrayList<>());
//          pet.setTags(new ArrayList<>());
//          pet.setStatus(Pet.Status.available);
//
//          return pet;
//     }
//
//     @Test
//     public void shouldUpdatePet() {
//          assertTrue(petService.updatePet(createNewPet()));
//     }
//}
