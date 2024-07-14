package com.example.PersonalFinanceManagementSystem.controller;

import com.example.PersonalFinanceManagementSystem.dto.UserDto;
import com.example.PersonalFinanceManagementSystem.model.User;
import com.example.PersonalFinanceManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@Param("id") Long id) {
        User currentUser = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setUsername(currentUser.getUsername());
        userDto.setEmail(currentUser.getEmail());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUserProfile(@RequestBody User updatedUser, @Param("id") Long id) {
        User currentUser = userService.getUserById(id);
        currentUser.setEmail(updatedUser.getEmail());
        // Add other fields you want to allow updating
        User savedUser = userService.updateUser(currentUser);
        UserDto userDto = new UserDto();
        userDto.setUsername(savedUser.getUsername());
        userDto.setEmail(savedUser.getEmail());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
