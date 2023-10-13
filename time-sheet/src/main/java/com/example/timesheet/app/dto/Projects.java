package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Projects {
    private List<ProjectDTO> projects;

    public Projects(List<ProjectDTO> projects){
        this.projects = projects;
    }
}
