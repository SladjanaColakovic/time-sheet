package com.example.timesheet;

import com.example.timesheet.app.dto.CategoryDTO;
import com.example.timesheet.app.dto.CategoryUpdateDTO;
import com.example.timesheet.app.dto.NewCategoryDTO;
import com.example.timesheet.core.model.Category;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.data.entity.CategoryEntity;
import com.example.timesheet.data.entity.ClientEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void clientToClientEntityUpdate(Client client, @MappingTarget ClientEntity clientEntity);

    Category newCategoryDTOToCategory(NewCategoryDTO newCategoryDTO);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryUpdateDTOToCategory(CategoryUpdateDTO categoryUpdateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryEntity categoryToCategoryEntity(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void categoryToCategoryEntityUpdate(Category category, @MappingTarget CategoryEntity categoryEntity);

    Category categoryEntityToCategory(CategoryEntity categoryEntity);


}
