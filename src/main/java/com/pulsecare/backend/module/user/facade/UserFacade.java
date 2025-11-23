package com.pulsecare.backend.module.user.facade;

import com.pulsecare.backend.module.doctordetail.model.DoctorDetail;
import com.pulsecare.backend.module.doctordetail.service.DoctorDetailService;
import com.pulsecare.backend.module.role.model.Role;
import com.pulsecare.backend.module.role.service.RoleService;
import com.pulsecare.backend.module.specialization.service.SpecializationService;
import com.pulsecare.backend.module.user.dto.UserRequestDTO;
import com.pulsecare.backend.module.user.dto.UserResponseDTO;
import com.pulsecare.backend.module.user.mapper.UserMapper;
import com.pulsecare.backend.module.user.model.Users;
import com.pulsecare.backend.module.user.service.UserService;
import com.pulsecare.backend.module.user.util.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserFacade {
    private final UserService userService;
    private final DoctorDetailService doctorDetailService;
    private final SpecializationService specializationService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, DoctorDetailService doctorDetailService,
                      SpecializationService specializationService, RoleService roleService,
                      UserMapper userMapper) {
        this.userService = userService;
        this.doctorDetailService = doctorDetailService;
        this.specializationService = specializationService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createNewUser(UserRequestDTO data) {
        Users userEntity = userMapper.toEntity(data);
        Set<Role> roles = roleService.findAllById(data.roles());
        userEntity.setRoles(roles);

        Users savedUser = userService.create(userEntity);
        boolean isDoctor = UserUtil.isRoleAvailable(roles, "DOCTOR");

        return setDoctorDetails(data, savedUser, isDoctor);
    }

    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO data, String id) {
        Users userEntity = userMapper.toEntity(data);
        Set<Role> roles = roleService.findAllById(data.roles());
        userEntity.setRoles(roles);

        Users updatedUser = userService.update(id, userEntity);
        boolean isDoctor = UserUtil.isRoleAvailable(roles, "DOCTOR");

        return setDoctorDetails(data, updatedUser, isDoctor);
    }

    private UserResponseDTO setDoctorDetails(UserRequestDTO data, Users updatedUser, boolean isDoctor) {
        if (isDoctor) {
            DoctorDetail doctorDetail = new DoctorDetail();
            doctorDetail.setUser(updatedUser);
            doctorDetail.setLicenseNo(data.doctorDetails().licenseNo());
            doctorDetail.setSpecializations(
                    specializationService.findAllById(
                            data.doctorDetails().specializationIds()
                    )
            );
            doctorDetailService.create(doctorDetail);
        }
        return userMapper.toDTO(updatedUser);
    }


}
