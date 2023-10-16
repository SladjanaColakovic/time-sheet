package com.example.timesheet.service;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.IProjectRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.ProjectConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectTests {

    @Mock
    private IProjectRepository projectRepository;

    @Mock
    private Project project;

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void testGetAllForAdmin() {
        when(projectRepository.getAll()).thenReturn(Arrays.asList(
                new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                        new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                                new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                        new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE)),
                new Project(NEW_ID, NEW_NAME, NEW_DESCRIPTION,
                        new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                                new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                        new TeamMember(NEW_TEAM_MEMBER_ID, NEW_TEAM_MEMBER_NAME, NEW_TEAM_MEMBER_USERNAME, NEW_TEAM_MEMBER_PASSWORD, NEW_TEAM_MEMBER_EMAIL, NEW_TEAM_MEMBER_HOURS_PER_WEEK, NEW_TEAM_MEMBER_STATUS, NEW_TEAM_MEMBER_ROLE))));

        List<Project> projects = projectService.getAll(1L, "ADMIN");

        assertEquals(2, projects.size());
        assertEquals(projects.get(0).getName(), DB_NAME);

        verify(projectRepository, times(1)).getAll();
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    public void testGetAllForTeamLeader() {
        when(projectRepository.getAll()).thenReturn(Arrays.asList(
                new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                        new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                                new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                        new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE)),
                new Project(NEW_ID, NEW_NAME, NEW_DESCRIPTION,
                        new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                                new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                        new TeamMember(NEW_TEAM_MEMBER_ID, NEW_TEAM_MEMBER_NAME, NEW_TEAM_MEMBER_USERNAME, NEW_TEAM_MEMBER_PASSWORD, NEW_TEAM_MEMBER_EMAIL, NEW_TEAM_MEMBER_HOURS_PER_WEEK, NEW_TEAM_MEMBER_STATUS, NEW_TEAM_MEMBER_ROLE))));        when(projectRepository.getLeadingProjects(DB_ID)).thenReturn(Arrays.asList(new Project(DB_ID, DB_NAME, DB_DESCRIPTION, new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE, new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)), new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE))));
        List<Project> projects = projectService.getAll(1L, "WORKER");

        assertEquals(1, projects.size());
        assertEquals(projects.get(0).getName(), DB_NAME);

        verify(projectRepository, times(1)).getLeadingProjects(1L);
    }

    @Test
    public void testGetById() {
        when(projectRepository.getById(DB_ID)).thenReturn(project);

        Project dbProject = projectService.getById(DB_ID);

        assertEquals(project, dbProject);

        verify(projectRepository, times(1)).getById(DB_ID);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @Transactional
    public void testCreate() {

        Project newProject = new Project(NEW_NAME, NEW_DESCRIPTION,
                new Client(NEW_CLIENT_ID, NEW_CLIENT_NAME, NEW_CLIENT_ADDRESS, NEW_CLIENT_CITY, NEW_CLIENT_POSTAL_CODE,
                        new Country(NEW_CLIENT_COUNTRY_ID, NEW_CLIENT_COUNTRY_NAME)),
                new TeamMember(NEW_TEAM_MEMBER_ID, NEW_TEAM_MEMBER_NAME, NEW_TEAM_MEMBER_USERNAME, NEW_TEAM_MEMBER_PASSWORD, NEW_TEAM_MEMBER_EMAIL, NEW_TEAM_MEMBER_HOURS_PER_WEEK, NEW_TEAM_MEMBER_STATUS, NEW_TEAM_MEMBER_ROLE));


        when(projectRepository.getAll()).thenReturn(Arrays.asList(new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                        new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE))));
        when(projectRepository.create(newProject)).thenReturn(newProject);

        int dbSizeBeforeAdd = projectRepository.getAll().size();

        Project dbProject = projectService.create(newProject);

        when(projectRepository.getAll()).thenReturn(Arrays.asList(new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                        new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE)),
                newProject));

        assertNotNull(dbProject);

        List<Project> projects = projectRepository.getAll();
        assertEquals(dbSizeBeforeAdd + 1, projects.size());

        Project saved = projects.get(projects.size() - 1);

        assertEquals(saved.getName(), NEW_NAME);

        verify(projectRepository, times(2)).getAll();
        verify(projectRepository, times(1)).create(newProject);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @Transactional
    public void testUpdate() {

        when(projectRepository.getById(DB_ID)).thenReturn(new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                        new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE)));

        Project projectForUpdate = projectService.getById(DB_ID);
        projectForUpdate.setName(NEW_NAME);

        when(projectRepository.create(projectForUpdate)).thenReturn(projectForUpdate);

        Project saved = projectService.create(projectForUpdate);

        assertNotNull(saved);

        Project dbProject = projectService.getById(DB_ID);
        assertEquals(dbProject.getName(), NEW_NAME);

        verify(projectRepository, times(2)).getById(DB_ID);
        verify(projectRepository, times(1)).create(projectForUpdate);
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @Transactional
    public void testDelete() {
        when(projectRepository.getAll()).thenReturn(Arrays.asList(
                new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                    new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                        new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                    new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE)),
                new Project(DB_ID_TO_DELETE, DB_NAME_TO_DELETE, DB_DESCRIPTION_TO_DELETE,
                        new Client(DB_CLIENT_ID_TO_DELETE, DB_CLIENT_NAME_TO_DELETE, DB_CLIENT_ADDRESS_TO_DELETE, DB_CLIENT_CITY_TO_DELETE, DB_CLIENT_POSTAL_CODE_TO_DELETE,
                                new Country(DB_CLIENT_COUNTRY_ID_TO_DELETE, DB_CLIENT_COUNTRY_NAME_TO_DELETE)),
                        new TeamMember(DB_TEAM_MEMBER_ID_TO_DELETE, DB_TEAM_MEMBER_NAME_TO_DELETE, DB_TEAM_MEMBER_USERNAME_TO_DELETE, DB_TEAM_MEMBER_PASSWORD_TO_DELETE, DB_TEAM_MEMBER_EMAIL_TO_DELETE, DB_TEAM_MEMBER_HOURS_PER_WEEK_TO_DELETE, DB_TEAM_MEMBER_STATUS_TO_DELETE, DB_TEAM_MEMBER_ROLE_TO_DELETE))
                ));
        doNothing().when(projectRepository).delete(DB_ID_TO_DELETE);
        when(projectRepository.getById(DB_ID_TO_DELETE)).thenReturn(null);

        int dbSizeBeforeDelete = projectRepository.getAll().size();
        projectService.delete(DB_ID_TO_DELETE);

        when(projectRepository.getAll()).thenReturn(Arrays.asList( new Project(DB_ID, DB_NAME, DB_DESCRIPTION,
                new Client(DB_CLIENT_ID, DB_CLIENT_NAME, DB_CLIENT_ADDRESS, DB_CLIENT_CITY, DB_CLIENT_POSTAL_CODE,
                        new Country(DB_CLIENT_COUNTRY_ID, DB_CLIENT_COUNTRY_NAME)),
                new TeamMember(DB_TEAM_MEMBER_ID, DB_TEAM_MEMBER_NAME, DB_TEAM_MEMBER_USERNAME, DB_TEAM_MEMBER_PASSWORD, DB_TEAM_MEMBER_EMAIL, DB_TEAM_MEMBER_HOURS_PER_WEEK, DB_TEAM_MEMBER_STATUS, DB_TEAM_MEMBER_ROLE))));

        List<Project> projects = projectRepository.getAll();
        assertEquals(dbSizeBeforeDelete -1, projects.size());

        Project dbproject = projectService.getById(DB_ID_TO_DELETE);
        assertNull(dbproject);

        verify(projectRepository, times(1)).delete(DB_ID_TO_DELETE);
        verify(projectRepository, times(2)).getAll();
        verify(projectRepository, times(1)).getById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(projectRepository);
    }
}
