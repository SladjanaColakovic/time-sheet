package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfo {
    private Long id;
    private String role;
    private String username;

    public UserInfo(Long id, String role, String username) {
        this.id = id;
        this.role = role;
        this.username = username;
    }
}
