package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamMemberTimeSheetItems {
    private List<TimeSheetItem> items;
    private float totalHours;

    public TeamMemberTimeSheetItems(List<TimeSheetItem> items, float totalHours) {
        this.items = items;
        this.totalHours = totalHours;
    }
}
