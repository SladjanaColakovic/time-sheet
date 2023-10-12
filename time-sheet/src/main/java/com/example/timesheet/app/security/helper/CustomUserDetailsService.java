package com.example.timesheet.app.security.helper;

import com.example.timesheet.core.exception.UserNotFoundException;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ITeamMemberService teamMemberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TeamMember user = teamMemberService.getByUsername(username);
        if (user == null) throw new UserNotFoundException();

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRole().toString(),
                user.getLastPasswordChangeDate()
        );

    }
}
