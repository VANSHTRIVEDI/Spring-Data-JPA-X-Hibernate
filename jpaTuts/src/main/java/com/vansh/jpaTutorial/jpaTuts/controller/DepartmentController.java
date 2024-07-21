package com.vansh.jpaTutorial.jpaTuts.controller;


import com.vansh.jpaTutorial.jpaTuts.entities.DepartmentEntity;
import com.vansh.jpaTutorial.jpaTuts.entities.EmployeeEntity;
import com.vansh.jpaTutorial.jpaTuts.services.DepartmentServices;
import com.vansh.jpaTutorial.jpaTuts.services.EmployeeServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }


    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity)
    {
       return   departmentServices.createDepartment(departmentEntity);
    }

    @GetMapping("/{departmentId}")
    public  DepartmentEntity findDepartmentById(@PathVariable Long departmentId)
    {
        return  departmentServices.getDepartmentById(departmentId);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public  DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId)
    {
        return  departmentServices.assignManagerToDepartment(departmentId,employeeId);
    }


    @GetMapping(path = "/getAssignedDepartmentOfEmployee/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfEmployee(@PathVariable Long employeeId)
    {
        return departmentServices.getAssignedDepartmentOfEmployee(employeeId);
    }


}
