package com.waterbyte.servicebackend.controller;

import java.util.List;

import com.waterbyte.servicebackend.dtos.ServiceDto;
import com.waterbyte.servicebackend.resources.ServiceResource;
import com.waterbyte.servicebackend.services.ServicesDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/serviceBackend/services")
public class ServicesRestController {
    @Autowired
    private ServicesDataService servicesDataService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<ServiceResource>> getAllServices() {
        return new HttpEntity<>(servicesDataService.getServiceResources());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<ServiceResource> addService(@RequestBody ServiceDto serviceDto) {
        return new HttpEntity<>(servicesDataService.addService(serviceDto));
    }

    @RequestMapping(value = "/{serviceId}", method = RequestMethod.DELETE)
    public HttpEntity<ServiceResource> deleteService(@PathVariable int serviceId) {
        return new HttpEntity<>(servicesDataService.deleteService(serviceId));
    }

    @RequestMapping(value = "/{serviceId}", method = RequestMethod.GET)
    public HttpEntity<ServiceResource> getService(@PathVariable int serviceId) {
        return new HttpEntity<>(servicesDataService.getService(serviceId));
    }

    @RequestMapping(value = "/{serviceId}", method = RequestMethod.PUT)
    public HttpEntity<ServiceResource> editService(@PathVariable int serviceId, @RequestBody ServiceDto serviceDto) {
        return new HttpEntity<>(servicesDataService.editService(serviceId, serviceDto));
    }

    @RequestMapping(value = "/{serviceId}/address", method = RequestMethod.GET)
    public HttpEntity<String> getServiceAddress(@PathVariable int serviceId) {
        return new HttpEntity<>(servicesDataService.getServiceAddress(serviceId));
    }
}
