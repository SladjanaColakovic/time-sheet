package com.example.timesheet.app.security.auth;

import com.example.timesheet.app.dto.LoginRequest;
import com.example.timesheet.app.dto.LoginResponse;
import com.example.timesheet.app.security.token.TokenUtils;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final ITeamMemberService teamMemberService;

    @Autowired
    public AuthenticationService(TokenUtils tokenUtils, AuthenticationManager authenticationManager, ITeamMemberService teamMemberService){
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.teamMemberService = teamMemberService;
    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        TeamMember user = teamMemberService.getByUsername(loginRequest.getUsername());
        String jwt = tokenUtils.generateToken(user);
        int expiresIn = tokenUtils.getExpiredIn();

        return new LoginResponse(jwt, expiresIn);
    }
}
