package com.example.timesheet;

import com.example.timesheet.app.dto.*;
import com.example.timesheet.core.model.*;
import com.example.timesheet.data.entity.*;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomMapper {

    Category newCategoryDTOToCategory(NewCategoryDTO newCategoryDTO);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryUpdateDTOToCategory(CategoryUpdateDTO categoryUpdateDTO);

    CategoryEntity categoryToCategoryEntity(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void categoryToCategoryEntityUpdate(Category category, @MappingTarget CategoryEntity categoryEntity);

    Category categoryEntityToCategory(CategoryEntity categoryEntity);

    Country newCountryDTOToCountry(NewCountryDTO newCountryDTO);

    CountryDTO countryToCountryDTO(Country country);

    Country countryUpdateDTOToCountry(CountryUpdateDTO countryUpdateDTO);

    CountryEntity countryToCountryEntity(Country country);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void countryToCountryEntityUpdate(Country country, @MappingTarget CountryEntity countryEntity);

    Country countryEntityToCountry(CountryEntity countryEntity);

    TeamMember newTeamMemberDTOToTeamMember(NewTeamMemberDTO newTeamMemberDTO);

    TeamMemberDTO teamMemberToTeamMemberDTO(TeamMember teamMember);

    TeamMember teamMemberUpdateDTOToTeamMember(TeamMemberUpdateDTO teamMemberUpdateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TeamMemberEntity teamMemberToTeamMemberEntity(TeamMember teamMember);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void teamMemberToTeamMemberEntityUpdate(TeamMember teamMember, @MappingTarget TeamMemberEntity teamMemberEntity);

    TeamMember teamMemberEntityToTeamMember(TeamMemberEntity teamMemberEntity);


    Client newClientDTOToClient(NewClientDTO newClientDTO);

    ClientDTO clientToClientDTO(Client client);

    Client clientUpdateDTOToClient(ClientUpdateDTO clientUpdateDTO);

    ClientEntity clientToClientEntity(Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void clientToClientEntityUpdate(Client client, @MappingTarget ClientEntity clientEntity);

    Client clientEntityToClient(ClientEntity clientEntity);

    Project newProjectDTOToProject(NewProjectDTO newProjectDTO);

    ProjectDTO projectToProjectDTO(Project project);

    Project projectUpdateDTOToProject(ProjectUpdateDTO projectUpdateDTO);

    ProjectEntity projectToProjectEntity(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void projectToProjectEntityUpdate(Project project, @MappingTarget ProjectEntity projectEntity);

    Project projectEntityToProject(ProjectEntity projectEntity);

    TimeSheetItem newTimeSheetItemDTOToTimeSheetItem(NewTimeSheetItemDTO newTimeSheetItemDTO);

    TimeSheetItemDTO timeSheetItemToTimeSheetItemDTO(TimeSheetItem timeSheetItem);

    TimeSheetItemEntity timeSheetItemToTimeSheetItemEntity(TimeSheetItem timeSheetItem);

    TimeSheetItem timeSheetItemEntityToTimeSheetItem(TimeSheetItemEntity timeSheetItemEntity);

    ReportSearch reportSearchDTOToReportSearch(ReportSearchDTO reportSearchDTO);

    ReportDTO reportToReportDTO(Report report);

    ChangePassword chnagePasswordDTOToChangePassword(ChangePasswordDTO changePasswordDTO);

    TimeSheetRange timeSheetRangeDTOToTimeSheetRange(TimeSheetRangeDTO timeSheetRangeDTO);

    TimeSheetDTO timeSheetToTimeSheetDTO(TimeSheet timeSheet);

}
