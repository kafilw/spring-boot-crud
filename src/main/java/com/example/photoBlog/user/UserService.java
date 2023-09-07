package com.example.photoBlog.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
    }

    public User registerNewUser(UserDTO userDTO) {
        // Create a new User entity and save it to the database
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Hash the password
        return userRepository.save(user);
    }

    public User updateUserPassword(String username, String newPassword) {
        // Find the user by username
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Update the password (remember to hash it)
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
        return null; // User not found
    }

    public boolean isUserLoggedIn(String username) {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    System.out.println("User " + username + " is logged in.");
                    return true;
                }
            }
        }
        System.out.println("User " + username + " is not logged in.");
        return false;
    }

    public ResponseEntity<String> loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. User not found.");
        }

        if (isUserLoggedIn(loginRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. User already logged in.");
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Incorrect password.");
        }
    }
    

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
