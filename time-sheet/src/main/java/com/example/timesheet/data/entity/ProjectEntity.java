package com.example.timesheet.data.entity;

import com.example.timesheet.data.enumeration.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ProjectStatus status;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    private TeamMemberEntity lead;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private Set<ProjectSheetEntity> projectSheets;



}
