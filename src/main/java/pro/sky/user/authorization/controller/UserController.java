package pro.sky.user.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.user.authorization.model.User;
import pro.sky.user.authorization.service.UserService;

@RestController
@RequestMapping("/fridge")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/entry")
    public ResponseEntity<?> loginToAccount(@RequestParam String userName,
                                            @RequestParam String userPassword) {

        return userService.findUser(userName, userPassword);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam String userName,
                                        @RequestParam String userPassword) {
        return userService.deleteUser(userName, userPassword);
    }
}
