package com.vansh.jpaTutorial.jpaTuts.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private  String title;

    @OneToOne
    @JoinColumn(name = "department_manager")
    private EmployeeEntity manager;


    @OneToMany(mappedBy = "workerDepartment",
                                fetch =FetchType.EAGER)
    private Set<EmployeeEntity> departmentEmployees;

    @ManyToMany(mappedBy = "freelanceDepartment")
    private Set<EmployeeEntity>freelancers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity entity = (DepartmentEntity) o;
        return Objects.equals(getId(), entity.getId()) && Objects.equals(getTitle(), entity.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
