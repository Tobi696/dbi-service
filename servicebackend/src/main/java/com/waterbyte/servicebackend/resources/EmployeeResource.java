package com.waterbyte.servicebackend.resources;

import lombok.Data;

@Data
public class EmployeeResource {
    private int id;
    private String name;
    private String longitude;
    private String latitude;
}