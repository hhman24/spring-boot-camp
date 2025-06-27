package com.java.spring_boot_camp.modules.users.repositories;

import com.java.spring_boot_camp.common.repositories.BaseRepository;
import com.java.spring_boot_camp.modules.users.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
