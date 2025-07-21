package com.java.spring_boot_camp.modules.users.mapper;

import com.java.spring_boot_camp.modules.users.dtos.requests.PermissionRequest;
import com.java.spring_boot_camp.modules.users.dtos.responses.PermissionResponse;
import com.java.spring_boot_camp.modules.users.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    //    @Mapping(source = "firstName", target = "lastName")
    //    @Mapping(target = "lastName", ignore = true)
    PermissionResponse toPermissionResponse(Permission permission);

}
