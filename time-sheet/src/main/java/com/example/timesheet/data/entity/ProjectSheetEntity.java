package com.example.timesheet.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProjectSheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.EAGER)
    private TeamMemberEntity teamMember;

    @OneToMany(mappedBy = "projectSheet", fetch = FetchType.EAGER)
    private Set<TimeSheetItemEntity> timeSheetItems;
}
