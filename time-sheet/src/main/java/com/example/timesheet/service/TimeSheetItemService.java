package com.example.timesheet.service;

import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.core.service.ITimeSheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSheetItemService implements ITimeSheetItemService {

    private final ITimeSheetItemRepository timeSheetItemRepository;

    @Autowired
    public TimeSheetItemService(ITimeSheetItemRepository timeSheetItemRepository){
        this.timeSheetItemRepository = timeSheetItemRepository;
    }

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
}
