package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private boolean isDeleted;
    private CountryDTO country;
    //private Set<ProjectDTO> projects;

}
