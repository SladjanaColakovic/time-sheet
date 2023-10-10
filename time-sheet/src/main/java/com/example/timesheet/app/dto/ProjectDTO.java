package com.example.timesheet.app.dto;

import com.example.timesheet.app.enumeration.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@NoArgsConstructor
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private boolean isDeleted;
    private ClientDTO client;

}
