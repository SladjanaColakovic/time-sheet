package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ReportSearch {
    private Long clientId;
    private Long teamMemberId;
    private Long categoryId;
    private Long projectId;
    private LocalDate startDate;
    private LocalDate endDate;
}
