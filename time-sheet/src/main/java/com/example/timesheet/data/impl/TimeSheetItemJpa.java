package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeSheetItemJpa implements ITimeSheetItemRepository {

    private final TimeSheetItemJpaRepository timeSheetItemJpaRepository;
    private final ModelMapper mapper;
    @Autowired
    public TimeSheetItemJpa(TimeSheetItemJpaRepository timeSheetItemJpaRepository, ModelMapper mapper){
        this.timeSheetItemJpaRepository = timeSheetItemJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public TimeSheetItem create(TimeSheetItem timeSheetItem) {
        TimeSheetItemEntity newEntity = mapper.map(timeSheetItem, TimeSheetItemEntity.class);
        TimeSheetItemEntity saved = timeSheetItemJpaRepository.save(newEntity);
        return mapper.map(saved, TimeSheetItem.class);
    }

    @Override
    public TimeSheetItem getById(Long id) {
        TimeSheetItemEntity timeSheetItem = timeSheetItemJpaRepository.findById(id).orElse(null);
        if(timeSheetItem == null) throw new ObjectNotFoundException();
        return mapper.map(timeSheetItem, TimeSheetItem.class);
    }

    @Override
    public List<TimeSheetItem> getAll() {
        List<TimeSheetItemEntity> items = timeSheetItemJpaRepository.findAll();
        return items
                .stream()
                .map(element -> mapper.map(element, TimeSheetItem.class))
                .collect(Collectors.toList());
    }
}
