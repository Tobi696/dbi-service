package com.waterbyte.servicebackend.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.icu.text.SimpleDateFormat;
import com.waterbyte.servicebackend.database.EmployeesRepository;
import com.waterbyte.servicebackend.database.ServicesRepository;
import com.waterbyte.servicebackend.dtos.ServiceDto;
import com.waterbyte.servicebackend.entities.EmployeeEntity;
import com.waterbyte.servicebackend.entities.ServiceEntity;
import com.waterbyte.servicebackend.exceptions.ServiceBadRequestErrorException;
import com.waterbyte.servicebackend.exceptions.ServiceNotFoundErrorException;
import com.waterbyte.servicebackend.resources.ServiceResource;

public class ServicesDataService {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private EmployeesRepository employeeRepository;

    public List<ServiceResource> getServiceResources() {
        List<ServiceResource> result = new ArrayList<ServiceResource>();
        for (ServiceEntity service : servicesRepository.findAll()) {
            result.add(convertServiceToServiceResource(service));
        }
        return result;
    }

    private ServiceResource convertServiceToServiceResource(ServiceEntity service) {
        ServiceResource serviceResource = new ServiceResource();
        serviceResource.setId(service.getId());
        serviceResource.setName(service.getName());
        serviceResource.setEmployeeId(service.getEmployee().getId());
        serviceResource.setDate(service.getDate().toString());
        serviceResource.setLatitude(String.valueOf(service.getLatitude()));
        serviceResource.setLongitude(String.valueOf(service.getLongitude()));
        return serviceResource;
    }

    public ServiceResource addService(ServiceDto serviceDto) {
        checkServiceDto(serviceDto);
        ServiceEntity newService = new ServiceEntity();
        newService.setName(serviceDto.getName());
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(serviceDto.getEmployeeId());
        newService.setEmployee(employee);
        newService.setAddress(serviceDto.getAddress());
        newService.setDate(LocalDate.parse(serviceDto.getDate()));
        newService.setLatitude(serviceDto.getLatitude());
        newService.setLongitude(serviceDto.getLongitude());
        newService = servicesRepository.save(newService);
        return convertServiceToServiceResource(newService);
    }

    private void checkServiceDto(ServiceDto serviceDto) {
        if (serviceDto.getName() == null || serviceDto.getName().length() <= 4) {
            throw new ServiceBadRequestErrorException("Service name is required");
        }
        if (!employeeRepository.findById(serviceDto.getEmployeeId()).isPresent()) {
            throw new ServiceNotFoundErrorException("Employee does not exist");
        }
        if (serviceDto.getDate() == null || serviceDto.getDate().isEmpty()) {
            throw new ServiceBadRequestErrorException("Service date is required");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy hh:MM");
        try {
            sdf.parse(serviceDto.getDate());
        } catch (ParseException e) {
            throw new ServiceNotFoundErrorException("Service date is invalid");
        }
        if (serviceDto.getAddress() == null || serviceDto.getAddress().length() <= 5) {
            throw new ServiceBadRequestErrorException("Service address is required");
        }
    }

    public ServiceResource deleteService(int serviceId) {
        Optional<ServiceEntity> service = servicesRepository.findById(serviceId);
        if (!service.isPresent()) {
            throw new ServiceNotFoundErrorException("Service does not exist");
        }
        servicesRepository.delete(service.get());
        return convertServiceToServiceResource(service.get());
    }

    public ServiceResource editService(int serviceId, ServiceDto serviceDto) {
        Optional<ServiceEntity> optService = servicesRepository.findById(serviceId);
        if (!optService.isPresent()) {
            throw new ServiceNotFoundErrorException("Service does not exist");
        }
        ServiceEntity service = optService.get();
        checkServiceDto(serviceDto);
        service.setName(serviceDto.getName());
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(serviceDto.getEmployeeId());
        service.setEmployee(employee);
        service.setAddress(serviceDto.getAddress());
        service.setDate(LocalDate.parse(serviceDto.getDate()));
        service.setLatitude(serviceDto.getLatitude());
        service.setLongitude(serviceDto.getLongitude());
        service = servicesRepository.save(service);
        return convertServiceToServiceResource(service);
    }

    public ServiceResource getService(int serviceId) {
        Optional<ServiceEntity> service = servicesRepository.findById(serviceId);
        if (!service.isPresent()) {
            throw new ServiceNotFoundErrorException("Service does not exist");
        }
        return convertServiceToServiceResource(service.get());
    }

    public String getServiceAddress(int serviceId) {
        Optional<ServiceEntity> service = servicesRepository.findById(serviceId);
        if (!service.isPresent()) {
            throw new ServiceNotFoundErrorException("Service does not exist");
        }
        return service.get().getAddress();
    }
}
