package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ReportSearchDTO {
    private Long clientId;
    private Long teamMemberId;
    private Long categoryId;
    private Long projectId;
    private LocalDate startDate;
    private LocalDate endDate;

}
