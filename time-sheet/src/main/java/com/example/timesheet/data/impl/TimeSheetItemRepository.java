package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeSheetItemRepository implements ITimeSheetItemRepository {

    private final TimeSheetItemJpaRepository timeSheetItemJpaRepository;
    private final CustomMapper mapper;
    @Autowired
    public TimeSheetItemRepository(TimeSheetItemJpaRepository timeSheetItemJpaRepository, CustomMapper mapper){
        this.timeSheetItemJpaRepository = timeSheetItemJpaRepository;
        this.mapper = mapper;
    }
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
}
