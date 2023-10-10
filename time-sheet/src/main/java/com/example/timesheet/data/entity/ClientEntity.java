package com.example.timesheet.data.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    private CountryEntity country;


    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ProjectEntity> projects;

}
