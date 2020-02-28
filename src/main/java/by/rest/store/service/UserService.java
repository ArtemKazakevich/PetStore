package by.rest.store.service;

import by.rest.store.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class UserService {
     public final Map<String, User> usersList;
     public final Map<Long, Long> tokens;
     
     public UserService(Map<String, User> usersList, Map<Long, Long> tokens) {
          this.usersList = usersList;
          this.tokens = tokens;
     }
     
     public Map<String, User> getUsersMap() {
          return usersList;
     }
     
     public Map<Long, Long> getTokens() {
          return tokens;
     }
     
     public void addUser(User user) {
          usersList.put(user.getUserName(), user);
     }
     
     public Long authentication(User newUser) {
          String login = newUser.getUserName();
          String password = newUser.getPassword();
          if (usersList.isEmpty()) {
               return null;
          }
          if (usersList.containsKey(login)) {
               if (usersList.get(login).getPassword().equals(password)) {
                    Long token = new Random().nextLong();
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
