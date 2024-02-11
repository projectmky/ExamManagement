package com.example.ExamManagement.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ExamManagement.user.Permission.*;


@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    APPLICANT(Set.of(

            APPLICANT_READ,
            APPLICANT_CREATE,
            APPLICANT_UPDATE


    )),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE
    ));


    private final Set<Permission> permissionSet;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissionSet()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}





