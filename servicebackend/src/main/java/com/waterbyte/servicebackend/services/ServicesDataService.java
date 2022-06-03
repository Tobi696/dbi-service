package com.waterbyte.servicebackend.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.waterbyte.servicebackend.database.ServicesRepository;
import com.waterbyte.servicebackend.dtos.ServiceDto;
import com.waterbyte.servicebackend.entities.EmployeeEntity;
import com.waterbyte.servicebackend.entities.ServiceEntity;
import com.waterbyte.servicebackend.resources.ServiceResource;

public class ServicesDataService {
    @Autowired
    private ServicesRepository servicesRepository;

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

    public ServiceResource deleteService(int serviceId) {
        ServiceEntity service = servicesRepository.findById(serviceId).get();
        servicesRepository.delete(service);
        return convertServiceToServiceResource(service);
    }

    public ServiceResource editService(int serviceId, ServiceDto serviceDto) {
        ServiceEntity service = servicesRepository.findById(serviceId).get();
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
        ServiceEntity service = servicesRepository.findById(serviceId).get();
        return convertServiceToServiceResource(service);
    }
}
