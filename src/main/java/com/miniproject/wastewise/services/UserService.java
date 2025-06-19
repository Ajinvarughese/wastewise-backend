package com.miniproject.wastewise.services;

import com.miniproject.wastewise.dto.Login;
import com.miniproject.wastewise.entities.User;
import com.miniproject.wastewise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

        private final UserRepository userRepository;


        // 🔹 Get all users
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        // 🔹 Get user by ID
        public User getUserById(Long id) {
            return userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        }

        // 🔹 Register new user (Resident, Driver, Admin)
        public User registerUser(User user) {
            return userRepository.save(user);
        }


    // Get user by email and verify password
    public User getUserByEmail(Login login) throws EntityNotFoundException {
        User findUser = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + login.getEmail()));

        if(login.getPassword().equals(findUser.getPassword()) && login.getRole().equals(findUser.getRole())) {
            return findUser;
        }
        throw new IllegalArgumentException("Wrong credentials");
    }

        // 🔹 Update user profile
        public User updateUser(Long id, User updatedUser) {
            return userRepository.findById(id).map(user -> {
                user.setFullName(updatedUser.getFullName());
                user.setEmail(updatedUser.getEmail());
                user.setAddress(updatedUser.getAddress());
                user.setDob(updatedUser.getDob());
                user.setRole(updatedUser.getRole());
                return userRepository.save(user);
            }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        }

        // 🔹 Delete user
        public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new EntityNotFoundException("User not found with ID: " + id);
            }
            userRepository.deleteById(id);
        }


    }
