package com.api.users.dto;

import com.api.users.domain.Role;

public class RoleDto {
    private Long id;
    private String authority;

    public RoleDto(){
    }

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
