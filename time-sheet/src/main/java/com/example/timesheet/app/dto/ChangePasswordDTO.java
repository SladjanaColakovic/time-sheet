package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ChangePasswordDTO {
    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
