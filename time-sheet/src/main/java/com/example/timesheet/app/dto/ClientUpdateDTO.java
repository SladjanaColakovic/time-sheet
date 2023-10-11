package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientUpdateDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String postalCode;
    private CountryDTO country;
}
