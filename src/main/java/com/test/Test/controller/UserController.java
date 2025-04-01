package com.test.Test.controller;


import com.test.Test.model.User;
import com.test.Test.model.UserDTO;
import com.test.Test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${input.file}")
    private String fileInput;

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        UserDTO userDTO = userService.getUserById(id);
        return userDTO != null ? ResponseEntity.ok(userDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) Optional<String> firstName,
                                  @RequestParam(required = false) Optional<String> lastName) {
        return userService.searchUsers(firstName, lastName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
        return updatedUserDTO != null ? ResponseEntity.ok(updatedUserDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/load")
    public String loadUsersFromCSV() {
        try {
            userService.loadUsersFromCSV(fileInput);
            return "Utenti caricati con successo!";
        } catch (IOException e) {
            return "Errore nel caricare gli utenti: " + e.getMessage();
        }
    }
}
