package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItemDTO {
    private Long id;
    private LocalDate date;
    private String description;
    private float time;
    private float overtime;
    private CategoryDTO category;
    private TeamMemberDTO teamMember;
    private ProjectDTO project;
    private ClientDTO client;
}
