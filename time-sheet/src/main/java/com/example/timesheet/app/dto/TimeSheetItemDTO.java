package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItemDTO {
    private long id;
    private LocalDate date;
    private String description;
    private float time;
    private float overtime;
    private CategoryDTO category;
    private ProjectSheetDTO projectSheet;
}
