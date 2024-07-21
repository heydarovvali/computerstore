package az.vali.computer_store.controller;

import az.vali.computer_store.entity.User;
import az.vali.computer_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User databaseUser = userService.addUser(user);
        return ResponseEntity.status(201).body(databaseUser);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUser());
    }
}
