package com.waterbyte.servicebackend.services;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.waterbyte.servicebackend.dtos.EmployeeDto;
import com.waterbyte.servicebackend.entities.Employee;
import com.waterbyte.servicebackend.resources.EmployeeResource;

public class EmployeeDataService {
    private HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
    private int nextId = 1;

    public void createInitialEmployees() {
        Employee employee = new Employee();
        employee.setId(nextId);
        nextId++;
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setLatitude(40.7128);
        employee.setLongitude(74.0060);
        employees.put(employee.getId(), employee);

        Employee employee2 = new Employee();
        employee2.setId(nextId);
        nextId++;
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setLatitude(40.7128);
        employee2.setLongitude(74.0060);
        employees.put(employee2.getId(), employee2);
    }

    public List<EmployeeResource> getEmployeeResources() {
        return employees.values().stream().map(employee -> convertEmployeeToEmployeeResource(employee))
                .collect(Collectors.toList());
    }

    private EmployeeResource convertEmployeeToEmployeeResource(Employee employee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setId(employee.getId());
        employeeResource.setName(employee.getFirstName() + " " + employee.getLastName());
        employeeResource.setLongitude(String.valueOf(employee.getLongitude()));
        employeeResource.setLatitude(String.valueOf(employee.getLatitude()));
        return employeeResource;
    }

    public EmployeeResource addEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = new Employee();
        newEmployee.setId(nextId);
        nextId++;
        String[] nameParts = employeeDto.getName().split(" ");
        newEmployee.setFirstName(nameParts[0]);
        newEmployee.setLastName(nameParts[1]);
        newEmployee.setLatitude(Double.parseDouble(employeeDto.getLatitude()));
        newEmployee.setLongitude(Double.parseDouble(employeeDto.getLongitude()));
        employees.put(newEmployee.getId(), newEmployee);
        return convertEmployeeToEmployeeResource(newEmployee);
    }
}
