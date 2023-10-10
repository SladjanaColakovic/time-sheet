package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private boolean isDeleted;
    private CountryDTO country;
    //private Set<ProjectDTO> projects;

}
