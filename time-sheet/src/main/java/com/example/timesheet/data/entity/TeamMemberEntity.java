package com.example.timesheet.data.entity;


import com.example.timesheet.data.enumeration.Role;
import com.example.timesheet.data.enumeration.TeamMemberStatus;
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
@SQLDelete(sql = "UPDATE team_member_entity SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class TeamMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private Float hoursPerWeek;

    @Enumerated(EnumType.ORDINAL)
    private TeamMemberStatus status;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "lead", fetch = FetchType.EAGER)
    private Set<ProjectEntity> leadingProjects;

    @ManyToMany
    @JoinTable(name = "time_sheet", joinColumns = @JoinColumn(name = "team_member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<ProjectEntity> workingProjects;
}
