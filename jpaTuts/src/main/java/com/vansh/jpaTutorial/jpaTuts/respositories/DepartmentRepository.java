package com.vansh.jpaTutorial.jpaTuts.respositories;

import com.vansh.jpaTutorial.jpaTuts.entities.DepartmentEntity;
import com.vansh.jpaTutorial.jpaTuts.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
