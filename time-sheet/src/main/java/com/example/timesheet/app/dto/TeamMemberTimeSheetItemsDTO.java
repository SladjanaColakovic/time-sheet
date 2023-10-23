package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TeamMemberTimeSheetItemsDTO {

    private List<TimeSheetItemDTO> items;
    private float totalHours;
}
