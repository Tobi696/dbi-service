package com.waterbyte.servicebackend.database;

import org.springframework.data.repository.CrudRepository;

import com.waterbyte.servicebackend.entities.EmployeeEntity;

public interface EmployeesRepository extends CrudRepository<EmployeeEntity, Integer> {

}
