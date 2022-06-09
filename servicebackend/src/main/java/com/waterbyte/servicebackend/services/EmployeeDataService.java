package com.waterbyte.servicebackend.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.waterbyte.servicebackend.database.EmployeesRepository;
import com.waterbyte.servicebackend.dtos.EmployeeDto;
import com.waterbyte.servicebackend.entities.EmployeeEntity;
import com.waterbyte.servicebackend.exceptions.EmployeeBadRequestErrorException;
import com.waterbyte.servicebackend.resources.EmployeeResource;

public class EmployeeDataService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public List<EmployeeResource> getEmployeeResources() {
        List<EmployeeResource> result = new ArrayList<EmployeeResource>();
        for (EmployeeEntity employee : employeesRepository.findAll()) {
            result.add(convertEmployeeToEmployeeResource(employee));
        }
        return result;
    }

    private EmployeeResource convertEmployeeToEmployeeResource(EmployeeEntity employee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setId(employee.getId());
        employeeResource.setName(employee.getFirstName() + " " + employee.getLastName());
        employeeResource.setLongitude(String.valueOf(employee.getLongitude()));
        employeeResource.setLatitude(String.valueOf(employee.getLatitude()));
        return employeeResource;
    }

    public EmployeeResource addEmployee(EmployeeDto employeeDto) {
        checkEmployeeDto(employeeDto);
        EmployeeEntity newEmployee = new EmployeeEntity();
        String[] nameParts = employeeDto.getName().split(" ");
        newEmployee.setFirstName(nameParts[0]);
        newEmployee.setLastName(nameParts[1]);
        newEmployee.setLatitude(Double.parseDouble(employeeDto.getLatitude()));
        newEmployee.setLongitude(Double.parseDouble(employeeDto.getLongitude()));
        newEmployee = employeesRepository.save(newEmployee);
        return convertEmployeeToEmployeeResource(newEmployee);
    }

    private void checkEmployeeDto(EmployeeDto employeeDto) {
        if (employeeDto.getName() == null || employeeDto.getName().isEmpty()) {
            throw new EmployeeBadRequestErrorException("Employee name is required");
        }
        if (employeeDto.getLongitude() == null || employeeDto.getLongitude().isEmpty()) {
            throw new EmployeeBadRequestErrorException("Employee longitude is required");
        }
        if (employeeDto.getLatitude() == null || employeeDto.getLatitude().isEmpty()) {
            throw new EmployeeBadRequestErrorException("Employee latitude is required");
        }
        if (employeeDto.getName().length() <= 4) {
            throw new EmployeeBadRequestErrorException("Employee name must be at least 5 characters");
        }
    }
}
