package com.waterbyte.servicebackend.controller;

import java.util.List;

import com.waterbyte.servicebackend.dtos.EmployeeDto;
import com.waterbyte.servicebackend.resources.EmployeeResource;
import com.waterbyte.servicebackend.services.EmployeeDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/serviceBackend/employees")
public class EmployeesRestController {
    @Autowired
    private EmployeeDataService employeeDataService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<EmployeeResource>> getAllEmployees() {
        return new HttpEntity<>(employeeDataService.getEmployeeResources());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<EmployeeResource> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return new HttpEntity<>(employeeDataService.addEmployee(employeeDto));
    }
}
