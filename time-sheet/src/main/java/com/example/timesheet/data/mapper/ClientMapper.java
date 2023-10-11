package com.example.timesheet.data.mapper;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.data.entity.ClientEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClient(Client client, @MappingTarget ClientEntity clientEntity);
}
