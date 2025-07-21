package com.java.spring_boot_camp.modules.users.mapper;

import com.java.spring_boot_camp.modules.users.dtos.requests.PermissionRequest;
import com.java.spring_boot_camp.modules.users.dtos.requests.RoleRequest;
import com.java.spring_boot_camp.modules.users.dtos.responses.PermissionResponse;
import com.java.spring_boot_camp.modules.users.dtos.responses.RoleResponse;
import com.java.spring_boot_camp.modules.users.entities.Permission;
import com.java.spring_boot_camp.modules.users.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    //    @Mapping(source = "firstName", target = "lastName")
    //    @Mapping(target = "lastName", ignore = true)
    RoleResponse toRoleResponse(Role role);

}
