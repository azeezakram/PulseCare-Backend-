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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

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
        if (isDoctor) {
            DoctorDetail newDoctorDetail = setDoctorDetails(data, savedUser);
            newDoctorDetail = doctorDetailService.create(newDoctorDetail);
            savedUser.setDoctorDetails(newDoctorDetail);
        }

        return userMapper.toDTO(savedUser);
    }


    @Transactional
    public UserResponseDTO updateUser(UserRequestDTO data, String id) {
        Users userEntity = userMapper.toEntity(data);
        Set<Role> roles = roleService.findAllById(data.roles());
        userEntity.setRoles(roles);

        Users updatedUser = userService.update(id, userEntity);

        if (userEntity.getDoctorDetails() != null) {
            boolean isDoctor = UserUtil.isRoleAvailable(roles, "DOCTOR");

            if (isDoctor) {
                DoctorDetail updatedDoctorDetail = setDoctorDetails(data, updatedUser);
                updatedDoctorDetail = doctorDetailService.update(updatedUser.getDoctorDetails().getId(), updatedDoctorDetail);
                updatedUser.setDoctorDetails(updatedDoctorDetail);
                return userMapper.toDTO(updatedUser);
            }
        }

        return userMapper.toDTO(updatedUser);


    }

    private DoctorDetail setDoctorDetails(UserRequestDTO data, Users savedOrUpdatedUser) {
        DoctorDetail doctorDetail = new DoctorDetail();
        doctorDetail.setUser(savedOrUpdatedUser);

        if (data.doctorDetails() != null) {
            // Normal case: populate licenseNo and specializations
            doctorDetail.setLicenseNo(data.doctorDetails().licenseNo());
            doctorDetail.setSpecializations(
                    specializationService.findAllById(data.doctorDetails().specializationIds())
            );
        } else {
            // Doctor details not provided, set licenseNo and specializations as null
            doctorDetail.setLicenseNo(null);
            doctorDetail.setSpecializations(null);
        }

        return doctorDetail;
    }



}
