package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItem {
    private Long id;
    private LocalDate date;
    private String description;
    private float time;
    private float overtime;
    private Category category;
    private TeamMember teamMember;
    private Project project;
}
