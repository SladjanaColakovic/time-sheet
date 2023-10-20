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

    public ReportSearchDTO(Long clientId, Long teamMemberId, Long categoryId, Long projectId, LocalDate startDate, LocalDate endDate) {
        this.clientId = clientId;
        this.teamMemberId = teamMemberId;
        this.categoryId = categoryId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
