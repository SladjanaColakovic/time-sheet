package com.example.timesheet.data.entity;

import com.example.timesheet.data.enumeration.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE project_entity SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
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

    @ManyToMany(mappedBy = "workingProjects")
    private Set<TeamMemberEntity> teamMembers;



}
