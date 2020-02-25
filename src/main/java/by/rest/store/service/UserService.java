package by.rest.store.service;

import by.rest.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class UserService {
     
     @Autowired
     public Map<String, User> usersList;
     
     @Autowired
     public Map<Long, String> tokens;
     
     public String authentication(User newUser) {
          String login = newUser.getUserName();
          String password = newUser.getPassword();
          if (usersList.isEmpty()) {
               return null;
          }
          if (usersList.containsKey(login)) {
               if (usersList.get(login).getPassword().equals(password)) {
                    String token = Integer.toString(new Random().nextInt());
                    tokens.put(usersList.get(login).getId(), token);
                    return token;
               }
          }
          return null;
     }
     
     public void addAllUsers(List<User> userList) {
          for (User user: userList) {
               usersList.put(user.getUserName(), user);
          }
     }
}
