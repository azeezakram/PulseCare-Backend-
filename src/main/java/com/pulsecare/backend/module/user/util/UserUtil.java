package com.pulsecare.backend.module.user.util;

import com.pulsecare.backend.module.role.model.Role;

import java.util.Set;

public class UserUtil {
    public static boolean isRoleAvailable(Set<Role> roles, String roleName) {
        return roles.stream()
                .anyMatch(r -> r.getName().equals(roleName));
    }
}
