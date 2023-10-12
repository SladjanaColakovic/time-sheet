package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private Country country;
    //private Set<Project> projects;
}
