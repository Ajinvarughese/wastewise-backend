package com.miniproject.wastewise.controller;

import com.miniproject.wastewise.dto.Login;
import com.miniproject.wastewise.entities.User;
import com.miniproject.wastewise.library.FileUpload;
import com.miniproject.wastewise.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
        private final UserService userService;
        private final FileUpload fileUpload;

        //get all user
        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
        }

        //get user by id
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return ResponseEntity.ok(userService.getUserById(id));
        }

        //create a user
        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            return ResponseEntity.ok(userService.registerUser(user));
        }

        @PostMapping("/login")
        public ResponseEntity<User> loginUser(@RequestBody Login loginCred) {
            return ResponseEntity.ok(userService.getUserByEmail(loginCred));
        }

        //update user
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
            return ResponseEntity.ok(userService.updateUser(id, user));
        }

         @PostMapping(path = "/file/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
        public ResponseEntity<String> addImage(@ModelAttribute("pictureUrl") MultipartFile file) throws IOException {
            String pictureUrl = fileUpload.uploadFile(file);
            return ResponseEntity.ok(pictureUrl);
        }
        //delete user
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
    }
