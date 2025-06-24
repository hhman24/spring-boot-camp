package com.java.spring_boot_camp.modules.users.controllers;

import com.java.spring_boot_camp.common.dtos.ApiResponse;
import com.java.spring_boot_camp.modules.users.dtos.requests.UserCreationRequest;
import com.java.spring_boot_camp.modules.users.dtos.requests.UserUpdateRequest;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> res = new ApiResponse<>();

        res.setData(userService.createRequest(request));

        return res;
    }

    @GetMapping()
    List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUserById(userId, request);
    }

}
