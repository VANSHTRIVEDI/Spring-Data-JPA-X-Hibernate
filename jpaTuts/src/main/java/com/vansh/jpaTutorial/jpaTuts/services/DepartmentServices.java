package com.vansh.jpaTutorial.jpaTuts.services;

import com.vansh.jpaTutorial.jpaTuts.entities.DepartmentEntity;
import com.vansh.jpaTutorial.jpaTuts.entities.EmployeeEntity;
import com.vansh.jpaTutorial.jpaTuts.respositories.DepartmentRepository;
import com.vansh.jpaTutorial.jpaTuts.respositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepartmentServices {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository  employeeRepository;

    public DepartmentServices(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository=departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public  DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
       Optional<DepartmentEntity>  departmentEntity=departmentRepository.findById(departmentId);
        Optional<EmployeeEntity>  employeeEntity=employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department->
            employeeEntity.map(employee -> {
                department.setManager(employee);
              return departmentRepository.save(department);
            })).orElse(null);

    }

    public DepartmentEntity createDepartment(DepartmentEntity entity)
    {
        return  departmentRepository.save(entity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfEmployee(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

//        return  employeeEntity.map(employee->employee.getManagedDepartment()).orElse(null);

        return departmentRepository.findByManager(employeeEntity.get());

    }
}
