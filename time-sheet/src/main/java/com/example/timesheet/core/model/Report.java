package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Report {
    private List<TimeSheetItem> items;
    private Float totalReport;

    public Report(List<TimeSheetItem> items, Float totalReport){
        this.items = items;
        this.totalReport = totalReport;
    }
}
