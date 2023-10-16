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


    public Client(Long id, String name, String address, String city, String postalCode, Country country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Client(String name, String address, String city, String postalCode, Country country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}
