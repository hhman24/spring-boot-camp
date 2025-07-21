package com.java.spring_boot_camp.modules.users.services;

import com.java.spring_boot_camp.modules.users.dtos.requests.PermissionRequest;
import com.java.spring_boot_camp.modules.users.dtos.responses.PermissionResponse;
import com.java.spring_boot_camp.modules.users.entities.Permission;
import com.java.spring_boot_camp.modules.users.mapper.PermissionMapper;
import com.java.spring_boot_camp.modules.users.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;


    Object create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = this.permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    List<PermissionResponse> getList() {
        var permissions = this.permissionRepository.findAll();

        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
