package com.example.timesheet.service;

import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.TeamMemberConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamMemberTests {
    @Mock
    private ITeamMemberRepository teamMemberRepository;

    @Mock
    private TeamMember teamMember;

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

    @Test
    public void testGetById() {
        when(teamMemberRepository.getById(DB_ID)).thenReturn(teamMember);

        TeamMember dbTeamMember = teamMemberService.getById(DB_ID);

        assertEquals(teamMember, dbTeamMember);

        verify(teamMemberRepository, times(1)).getById(DB_ID);
        verifyNoMoreInteractions(teamMemberRepository);
    }

    @Test
    @Transactional
    public void testCreate() {

        TeamMember newTeamMember = new TeamMember(NEW_NAME, NEW_USERNAME, NEW_PASSWORD, NEW_EMAIL, NEW_HOURS_PER_WEEK, NEW_STATUS, NEW_ROLE);

        when(teamMemberRepository.getAll()).thenReturn(Arrays.asList(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE)));
        when(teamMemberRepository.create(newTeamMember)).thenReturn(newTeamMember);

        int dbSizeBeforeAdd = teamMemberService.getAll().size();

        TeamMember dbTeamMember = teamMemberService.create(newTeamMember);

        when(teamMemberRepository.getAll()).thenReturn(Arrays.asList(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE), newTeamMember));

        assertNotNull(dbTeamMember);

        List<TeamMember> teamMembers = teamMemberService.getAll();
        assertEquals(dbSizeBeforeAdd + 1, teamMembers.size());

        TeamMember saved = teamMembers.get(teamMembers.size() - 1);

        assertEquals(saved.getName(), NEW_NAME);

        verify(teamMemberRepository, times(2)).getAll();
        verify(teamMemberRepository, times(1)).create(newTeamMember);
        verifyNoMoreInteractions(teamMemberRepository);
    }

    @Test
    @Transactional
    public void testUpdate() {

        when(teamMemberRepository.getById(DB_ID)).thenReturn(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE));

        TeamMember teamMemberForUpdate = teamMemberService.getById(DB_ID);
        teamMemberForUpdate.setName(NEW_NAME);

        when(teamMemberRepository.create(teamMemberForUpdate)).thenReturn(teamMemberForUpdate);

        TeamMember saved = teamMemberService.create(teamMemberForUpdate);

        assertNotNull(saved);

        TeamMember dbTeamMember = teamMemberService.getById(DB_ID);
        assertEquals(dbTeamMember.getName(), NEW_NAME);

        verify(teamMemberRepository, times(2)).getById(DB_ID);
        verify(teamMemberRepository, times(1)).create(teamMemberForUpdate);
        verifyNoMoreInteractions(teamMemberRepository);
    }

    @Test
    @Transactional
    public void testDelete() {
        when(teamMemberRepository.getAll()).thenReturn(Arrays.asList(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE),
                new TeamMember(DB_ID_TO_DELETE, DB_NAME_TO_DELETE, DB_USERNAME_TO_DELETE, DB_PASSWORD_TO_DELETE, DB_EMAIL_TO_DELETE, DB_HOURS_PER_WEEK_TO_DELETE, DB_STATUS_TO_DELETE, DB_ROLE_TO_DELETE)));
        doNothing().when(teamMemberRepository).delete(DB_ID_TO_DELETE);
        when(teamMemberRepository.getById(DB_ID_TO_DELETE)).thenReturn(null);

        int dbSizeBeforeDelete = teamMemberService.getAll().size();
        teamMemberService.delete(DB_ID_TO_DELETE);

        when(teamMemberService.getAll()).thenReturn(Arrays.asList(new TeamMember(DB_ID, DB_NAME, DB_USERNAME, DB_PASSWORD, DB_EMAIL, DB_HOURS_PER_WEEK, DB_STATUS, DB_ROLE)));

        List<TeamMember> teamMembers = teamMemberService.getAll();
        assertEquals(dbSizeBeforeDelete -1, teamMembers.size());

        TeamMember dbTeamMember = teamMemberService.getById(DB_ID_TO_DELETE);
        assertNull(dbTeamMember);

        verify(teamMemberRepository, times(1)).delete(DB_ID_TO_DELETE);
        verify(teamMemberRepository, times(2)).getAll();
        verify(teamMemberRepository, times(1)).getById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(teamMemberRepository);
    }
}
