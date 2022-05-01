package com.waterbyte.servicebackend;

import com.waterbyte.servicebackend.services.EmployeeDataService;
import com.waterbyte.servicebackend.services.ServicesDataService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public EmployeeDataService createEmployeeDataService() {
        EmployeeDataService employeeDataService = new EmployeeDataService();
        employeeDataService.createInitialEmployees();
        return employeeDataService;
    }

    @Bean
    public ServicesDataService createServicesDataService() {
        ServicesDataService servicesDataService = new ServicesDataService();
        servicesDataService.createInitialServices();
        return servicesDataService;
    }
}
