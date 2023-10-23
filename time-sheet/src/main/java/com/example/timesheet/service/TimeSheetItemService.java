package com.example.timesheet.service;

import com.example.timesheet.core.model.TeamMemberTimeSheetItems;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.core.service.ITimeSheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSheetItemService implements ITimeSheetItemService {

    @Autowired
    private ITimeSheetItemRepository timeSheetItemRepository;

    @Override
    public TimeSheetItem create(TimeSheetItem timeSheetItem) {
        return timeSheetItemRepository.create(timeSheetItem);
    }

    @Override
    public TimeSheetItem getById(Long id) {
        return timeSheetItemRepository.getById(id);
    }

    @Override
    public List<TimeSheetItem> getAll() {
        return timeSheetItemRepository.getAll();
    }

    @Override
    public TeamMemberTimeSheetItems getTeamMemberItems(Long teamMemberId, LocalDate date) {
        List<TimeSheetItem> items = timeSheetItemRepository.getTeamMemberItems(teamMemberId, date);
        float totalHours = calculateTotalHours(items);
        return new TeamMemberTimeSheetItems(items, totalHours);
    }

    private float calculateTotalHours(List<TimeSheetItem> timeSheetItems) {
        return timeSheetItems.stream()
                .map(element -> element.getTime() + element.getOvertime())
                .reduce(0F, Float::sum);

    }
}
