package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Countries {
    private List<CountryDTO> countries;
    public Countries(List<CountryDTO> countries){
        this.countries = countries;
    }
}
