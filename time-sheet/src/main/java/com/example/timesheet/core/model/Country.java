package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Country {
    private Long id;
    private String name;
    public Country(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
