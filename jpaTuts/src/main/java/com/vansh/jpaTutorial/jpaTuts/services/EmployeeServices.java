package com.vansh.jpaTutorial.jpaTuts.services;

import com.vansh.jpaTutorial.jpaTuts.entities.EmployeeEntity;
import com.vansh.jpaTutorial.jpaTuts.respositories.EmployeeRepository;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServices {

  private final EmployeeRepository employeeRepository;

  public EmployeeServices(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    public EmployeeEntity createEmployee(EmployeeEntity entity)
    {
        return  employeeRepository.save(entity);
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
