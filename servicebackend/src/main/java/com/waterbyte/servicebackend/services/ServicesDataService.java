package com.waterbyte.servicebackend.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.waterbyte.servicebackend.dtos.ServiceDto;
import com.waterbyte.servicebackend.entities.Service;
import com.waterbyte.servicebackend.resources.ServiceResource;

public class ServicesDataService {
    private HashMap<Integer, Service> services = new HashMap<Integer, Service>();
    private int nextId = 1;

    public void createInitialServices() {
        Service service = new Service();
        service.setId(nextId);
        nextId++;
        service.setName("Water");
        service.setEmployeeId(1);
        service.setAddress("123 Main St");
        service.setDate(LocalDate.now());
        service.setLatitude(40.7128);
        service.setLongitude(74.0060);
        services.put(service.getId(), service);

        Service service2 = new Service();
        service2.setId(nextId);
        nextId++;
        service2.setName("Electricity");
        service2.setEmployeeId(1);
        service2.setAddress("123 Main St");
        service2.setDate(LocalDate.now());
        service2.setLatitude(40.7128);
        service2.setLongitude(74.0060);
        services.put(service2.getId(), service2);
    }

    public List<ServiceResource> getServiceResources() {
        return services.values().stream().map(service -> convertServiceToServiceResource(service))
                .collect(Collectors.toList());
    }

    private ServiceResource convertServiceToServiceResource(Service service) {
        ServiceResource serviceResource = new ServiceResource();
        serviceResource.setId(service.getId());
        serviceResource.setName(service.getName());
        serviceResource.setEmployeeId(service.getEmployeeId());
        serviceResource.setDate(service.getDate().toString());
        serviceResource.setLatitude(String.valueOf(service.getLatitude()));
        serviceResource.setLongitude(String.valueOf(service.getLongitude()));
        return serviceResource;
    }

    public ServiceResource addService(ServiceDto serviceDto) {
        Service newService = new Service();
        newService.setId(nextId);
        nextId++;
        newService.setName(serviceDto.getName());
        newService.setEmployeeId(serviceDto.getEmployeeId());
        newService.setAddress(serviceDto.getAddress());
        newService.setDate(LocalDate.parse(serviceDto.getDate()));
        newService.setLatitude(serviceDto.getLatitude());
        newService.setLongitude(serviceDto.getLongitude());
        services.put(newService.getId(), newService);
        return convertServiceToServiceResource(newService);
    }

    public ServiceResource deleteService(int serviceId) {
        Service service = services.get(serviceId);
        services.remove(serviceId);
        return convertServiceToServiceResource(service);
    }

    public ServiceResource editService(int serviceId, ServiceDto serviceDto) {
        Service service = services.get(serviceId);
        service.setName(serviceDto.getName());
        service.setEmployeeId(serviceDto.getEmployeeId());
        service.setAddress(serviceDto.getAddress());
        service.setDate(LocalDate.parse(serviceDto.getDate()));
        service.setLatitude(serviceDto.getLatitude());
        service.setLongitude(serviceDto.getLongitude());
        services.put(service.getId(), service);
        return convertServiceToServiceResource(service);
    }

    public ServiceResource getService(int serviceId) {
        return convertServiceToServiceResource(services.get(serviceId));
    }
}
