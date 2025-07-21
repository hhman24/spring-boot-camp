package com.java.spring_boot_camp.modules.users.repositories;

import com.java.spring_boot_camp.common.repositories.BaseRepository;
import com.java.spring_boot_camp.modules.users.entities.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, String> {}
