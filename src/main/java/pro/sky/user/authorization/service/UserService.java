package pro.sky.user.authorization.service;

import org.springframework.http.ResponseEntity;
import pro.sky.user.authorization.model.User;

public interface UserService {

    ResponseEntity<?> saveUser(User user);


    ResponseEntity<?> findUser(String name, String password);

    ResponseEntity<?> deleteUser(String name, String password);
}
