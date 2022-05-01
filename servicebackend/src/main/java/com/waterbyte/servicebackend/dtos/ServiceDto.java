package com.waterbyte.servicebackend.dtos;

import lombok.Data;

@Data
public class ServiceDto {
    private String name;
    private int employeeId;
    private String date;
    private String address;
    private double latitude;
    private double longitude;
}