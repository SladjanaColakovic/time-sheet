package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItemUpdateDTO {
    private Long id;
    private String description;
    private float time;
    private float overtime;
    private ProjectDTO project;
    private CategoryDTO category;
}
