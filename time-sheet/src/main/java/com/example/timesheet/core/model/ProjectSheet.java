package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ProjectSheet {
    private long id;
    private Project project;
    private TeamMember teamMember;
    private Set<TimeSheetItem> timeSheetItems;
}
