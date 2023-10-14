package com.example.timesheet.app.security;

import com.example.timesheet.app.security.token.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomUserSecurity {


    private final TokenUtils tokenUtils;

    @Autowired
    public CustomUserSecurity(TokenUtils tokenUtils){
        this.tokenUtils = tokenUtils;
    }

    public boolean hasAccess(Authentication authentication, String username) {
       String user = authentication.getName();
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) || user.equals(username))
            return  true;
        return  false;
    }
}
