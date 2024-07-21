package com.vansh.jpaTutorial.jpaTuts.controller;


import com.vansh.jpaTutorial.jpaTuts.entities.DepartmentEntity;
import com.vansh.jpaTutorial.jpaTuts.entities.EmployeeEntity;
import com.vansh.jpaTutorial.jpaTuts.services.DepartmentServices;
import com.vansh.jpaTutorial.jpaTuts.services.EmployeeServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    final EmployeeServices employeeServices;

    public EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity)
    {
        return   employeeServices.createEmployee(employeeEntity);
    }

    @GetMapping("/{employeeId}")
    public  EmployeeEntity findEmployeeById(@PathVariable Long employeeId)
    {
        return  employeeServices.getEmployeeById(employeeId);
    }
}
