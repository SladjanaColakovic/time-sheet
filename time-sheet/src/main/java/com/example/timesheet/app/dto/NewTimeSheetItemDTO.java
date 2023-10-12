package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class NewTimeSheetItemDTO {
    private LocalDate date;
    private String description;
    private Float time;
    private Float overtime;
    private ProjectDTO project;
    private CategoryDTO category;
    private TeamMemberDTO teamMember;
}
