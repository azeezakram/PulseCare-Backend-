package com.pulsecare.backend.module.user.facade;

import com.pulsecare.backend.module.doctordetail.service.DoctorDetailService;
import com.pulsecare.backend.module.role.service.RoleService;
import com.pulsecare.backend.module.specialization.service.SpecializationService;
import com.pulsecare.backend.module.user.mapper.UserMapper;
import com.pulsecare.backend.module.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {
    private final UserService userService;
    private final DoctorDetailService doctorDetailService;
    private final SpecializationService specializationService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public UserFacade(UserService userService, DoctorDetailService doctorDetailService, SpecializationService specializationService, RoleService roleService, UserMapper userMapper) {
        this.userService = userService;
        this.doctorDetailService = doctorDetailService;
        this.specializationService = specializationService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

}
