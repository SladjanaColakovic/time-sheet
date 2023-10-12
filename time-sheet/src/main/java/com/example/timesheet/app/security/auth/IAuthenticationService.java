package com.example.timesheet.app.security.auth;

import com.example.timesheet.app.dto.LoginRequest;
import com.example.timesheet.app.dto.LoginResponse;

public interface IAuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
