package com.example.timesheet.app.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ProjectSheetDTO {
    private Long id;
    private ProjectDTO project;
    private TeamMemberDTO teamMember;
    private Set<TimeSheetItemDTO> timeSheetItems;
}
