package by.rest.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class StoreApplication {

     public static void main(String[] args) {
          SpringApplication.run(StoreApplication.class, args);
          log.info("The program is running");
     }

}
