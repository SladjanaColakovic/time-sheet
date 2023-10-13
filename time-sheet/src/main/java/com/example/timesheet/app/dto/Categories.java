package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Categories {
    private List<CategoryDTO> categories;
    public Categories(List<CategoryDTO> categories){
        this.categories = categories;
    }
}
