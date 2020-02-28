package by.rest.store.service;

import by.rest.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class UserService {
     public Map<String, User> usersList;
     public Map<Long, String> tokens;
     
     public UserService(Map<String, User> usersList, Map<Long, String> tokens) {
          this.usersList = usersList;
          this.tokens = tokens;
     }
     
     public Map<String, User> getUsersMap() {
          return usersList;
     }
     
     public Map<Long, String> getTokens() {
          return tokens;
     }
     
     public void addUser(User user) {
          usersList.put(user.getUserName(), user);
     }
     
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
