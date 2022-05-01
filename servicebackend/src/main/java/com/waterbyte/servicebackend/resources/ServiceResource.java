package com.waterbyte.servicebackend.resources;

import lombok.Data;

@Data
public class ServiceResource {
    private int id;
    private String name;
    private int employeeId;
    private String date;
    private String longitude;
    private String latitude;
}