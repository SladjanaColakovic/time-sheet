package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItems {
    private List<TimeSheetItemDTO> items;

    public TimeSheetItems(List<TimeSheetItemDTO> items){
        this.items = items;
    }
}
