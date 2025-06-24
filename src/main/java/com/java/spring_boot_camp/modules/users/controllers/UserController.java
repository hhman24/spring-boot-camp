package com.java.spring_boot_camp.modules.users.controllers;

import com.java.spring_boot_camp.modules.users.dtos.requests.UserCreationRequest;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createRequest(request);
    }

}
