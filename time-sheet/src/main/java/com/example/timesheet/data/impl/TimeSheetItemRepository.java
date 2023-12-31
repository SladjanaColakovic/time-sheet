package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeSheetItemRepository implements ITimeSheetItemRepository {

    @Autowired
    private  TimeSheetItemJpaRepository timeSheetItemJpaRepository;

    @Autowired
    private  CustomMapper mapper;

    @Override
    public TimeSheetItem create(TimeSheetItem timeSheetItem) {
        TimeSheetItemEntity newEntity = mapper.timeSheetItemToTimeSheetItemEntity(timeSheetItem);
        TimeSheetItemEntity saved = timeSheetItemJpaRepository.save(newEntity);
        return mapper.timeSheetItemEntityToTimeSheetItem(saved);
    }

    @Override
    public TimeSheetItem getById(Long id) {
        TimeSheetItemEntity timeSheetItem = timeSheetItemJpaRepository.findById(id).orElse(null);
        if(timeSheetItem == null) throw new ObjectNotFoundException();
        return mapper.timeSheetItemEntityToTimeSheetItem(timeSheetItem);
    }

    @Override
    public List<TimeSheetItem> getAll() {
        List<TimeSheetItemEntity> items = timeSheetItemJpaRepository.findAll();
        return items
                .stream()
                .map(mapper::timeSheetItemEntityToTimeSheetItem)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSheetItem> reportSearch(ReportSearch reportSearch) {
        List<TimeSheetItemEntity> items = timeSheetItemJpaRepository.reportSearch(reportSearch);
        return items
                .stream()
                .map(mapper::timeSheetItemEntityToTimeSheetItem)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeSheetItem> getTeamMemberItems(Long teamMemberId, LocalDate date) {
        List<TimeSheetItemEntity> items = timeSheetItemJpaRepository.getTeamMemberItems(teamMemberId, date);
        return items
                .stream()
                .map(mapper::timeSheetItemEntityToTimeSheetItem)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSheetItem update(TimeSheetItem timeSheetItem) {
        TimeSheetItemEntity editing = timeSheetItemJpaRepository.findById(timeSheetItem.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.timeSheetItemToTimeSheetItemEntityUpdate(timeSheetItem, editing);
        TimeSheetItemEntity saved = timeSheetItemJpaRepository.save(editing);
        return mapper.timeSheetItemEntityToTimeSheetItem(saved);
    }
}
