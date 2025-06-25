package com.java.spring_boot_camp.modules.users.services;

import com.java.spring_boot_camp.modules.users.dtos.requests.UserCreationRequest;
import com.java.spring_boot_camp.modules.users.dtos.requests.UserUpdateRequest;
import com.java.spring_boot_camp.modules.users.dtos.responses.UserResponse;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.mapper.UserMapper;
import com.java.spring_boot_camp.modules.users.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // inject container and constructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createRequest(UserCreationRequest request) {
//        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User exists");
        }

        // common pattern --> clean code
//        UserCreationRequest request1 = UserCreationRequest.builder().username("an").build();

        User user = userMapper.toUser(request);

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public User updateUserById(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());

        return userRepository.save(user);
    }
}
