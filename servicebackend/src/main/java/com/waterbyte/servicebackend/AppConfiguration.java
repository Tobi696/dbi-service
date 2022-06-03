package com.waterbyte.servicebackend;

import com.waterbyte.servicebackend.services.EmployeeDataService;
import com.waterbyte.servicebackend.services.ServicesDataService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public EmployeeDataService createEmployeeDataService() {
        return new EmployeeDataService();
    }

    @Bean
    public ServicesDataService createServicesDataService() {
        return new ServicesDataService();
    }
}
