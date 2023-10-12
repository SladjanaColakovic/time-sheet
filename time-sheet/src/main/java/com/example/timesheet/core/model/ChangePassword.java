package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChangePassword {
    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
