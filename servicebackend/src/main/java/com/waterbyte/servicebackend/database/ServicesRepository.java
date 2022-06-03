package com.waterbyte.servicebackend.database;

import org.springframework.data.repository.CrudRepository;

import com.waterbyte.servicebackend.entities.ServiceEntity;

public interface ServicesRepository extends CrudRepository<ServiceEntity, Integer> {

}