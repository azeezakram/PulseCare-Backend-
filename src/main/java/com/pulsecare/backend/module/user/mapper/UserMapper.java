package com.pulsecare.backend.module.user.mapper;

import com.pulsecare.backend.module.user.dto.LoginRequestDTO;
import com.pulsecare.backend.module.user.dto.UserRequestDTO;
import com.pulsecare.backend.module.user.dto.UserResponseDTO;
import com.pulsecare.backend.module.user.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "doctorDetails", ignore = true)
    Users toEntity(UserRequestDTO dto);
    UserResponseDTO toDTO(Users entity);
    Users toEntity(LoginRequestDTO dto);
}
