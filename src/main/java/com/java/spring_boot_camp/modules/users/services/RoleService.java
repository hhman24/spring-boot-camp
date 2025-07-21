package com.java.spring_boot_camp.modules.users.services;

import com.java.spring_boot_camp.modules.users.dtos.requests.RoleRequest;
import com.java.spring_boot_camp.modules.users.dtos.responses.RoleResponse;
import com.java.spring_boot_camp.modules.users.mapper.RoleMapper;
import com.java.spring_boot_camp.modules.users.repositories.PermissionRepository;
import com.java.spring_boot_camp.modules.users.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        var role = this.roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

}
