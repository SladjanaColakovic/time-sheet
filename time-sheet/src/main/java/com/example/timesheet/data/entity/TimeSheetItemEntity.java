package com.example.timesheet.data.entity;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.model.TeamMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class TimeSheetItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @Column(nullable = false)
    private float time;

    private float overtime;

    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.EAGER)
    private TeamMemberEntity teamMember;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

}
