package com.java.spring_boot_camp.modules.users.services;

import com.java.spring_boot_camp.modules.users.dtos.requests.UserCreationRequest;
import com.java.spring_boot_camp.modules.users.entities.User;
import com.java.spring_boot_camp.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());

        return userRepository.save(user);
    }
}
