package com.example.timesheet.service;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.TeamMemberConstants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamMemberTests {
    @Mock
    private ITeamMemberRepository teamMemberRepository;

    @InjectMocks
    private TeamMemberService teamMemberService;

    @Test
    public void testGetAll() {
        when(teamMemberRepository.getAll()).thenReturn(Arrays.asList(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE)));

        List<TeamMember> teamMembers = teamMemberService.getAll();

        assertEquals(1,teamMembers.size());
        assertEquals(teamMembers.get(0).getName(), DB_NAME);

        verify(teamMemberRepository, times(1)).getAll();
        verifyNoMoreInteractions(teamMemberRepository);
    }
}
