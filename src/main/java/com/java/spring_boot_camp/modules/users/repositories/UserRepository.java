package com.java.spring_boot_camp.modules.users.repositories;

import com.java.spring_boot_camp.common.repositories.BaseRepository;
import com.java.spring_boot_camp.modules.users.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, String> {
    boolean existsByUsername(String userName);
}
