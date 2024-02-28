package com.governance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.governance.DTO.UserDTO;
import com.governance.entity.User;
import com.governance.exception.CustomException;
import com.governance.exception.PasswordMissmatchException;
import com.governance.exception.UserNotFound;
import com.governance.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Autowired
   private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid input during user creation: {}", bindingResult.getAllErrors());
            return new ResponseEntity<>("Invalid input", HttpStatus.CONFLICT);
        }

        try {
            User createdUser = userService.createUser(user);
            logger.info("User created successfully: {}", createdUser);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (CustomException e) {
            logger.error("Error creating user: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.loginUser(email.toLowerCase(), password);
            logger.info("User logged in successfully: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFound | PasswordMissmatchException e) {
            logger.error("Error during user login: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
