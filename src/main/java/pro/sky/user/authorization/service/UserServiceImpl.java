package pro.sky.user.authorization.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.sky.user.authorization.model.User;
import pro.sky.user.authorization.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> saveUser(User user){
        if(userRepository.findByUserName(user.getUserName()) != null){
            return ResponseEntity.status(400).body("Пользователь с таким логином уже существует");
        }

        if ((user.getUserName() == null || user.getUserName().isEmpty())) {
            return ResponseEntity.status(400).body("Логин должен быть заполнен");
        }
        if(user.getUserPassword() == null || user.getUserPassword().isEmpty()){
            return ResponseEntity.status(400).body("Пароль должен быть заполнен");
        }

        if (!user.getUserPassword().matches("[a-zA-Z0-9!#%._\\-()]+") ||
                user.getUserPassword().length() < 6) {
            return ResponseEntity.status(400).body("Пароль должен быть длиннее 6 символов и " +
                    "содержать только латинские буквы, цифры и следующие символы:" +
                    "! # % . _ - ( )");
        }

        userRepository.save(user);

        return ResponseEntity.ok("Ваш аккаунт создан");
    }

    @Override
    public ResponseEntity<?> findUser(String name, String password) {
        if (userRepository.findByUserName(name).getUserName().equals(name) ||
                userRepository.findByUserPassword(password).getUserPassword().equals(password)) {
            return ResponseEntity.ok("Успешный вход в приложение");
        }else return ResponseEntity.status(500).body("Аккаунт с таким логином и паролем не найден");
    }

    @Override
    public ResponseEntity<?> deleteUser(String name, String password) {
        User user = new User();
        user.setUserName(name);
        user.setUserPassword(password);
        userRepository.delete(user);
        return ResponseEntity.ok("Аккаунт удален");
    }
}
