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
    private float time;
    private float overtime;
    private ClientDTO client;
    private ProjectDTO project;
    private CategoryDTO category;
    private TeamMemberDTO teamMember;
}
