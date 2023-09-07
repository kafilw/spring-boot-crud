package com.example.photoBlog.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        User newUser = userService.registerNewUser(userDTO);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        ResponseEntity<String> response = userService.loginUser(loginRequest);
        return response;
    }



    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        String username = userDetails.getUsername();
        User updatedUser = userService.updateUserPassword(username, updatePasswordRequest.getNewPassword());
        if (updatedUser != null) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password update failed");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        // You can implement logout logic here if needed
        // For simplicity, we'll return a message indicating logout
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/index")
    public String redirectToHtmlPage() {
        // Redirect to the HTML page
        return "redirect:/static/index.html"; // Change the path to your HTML page
    }
}
