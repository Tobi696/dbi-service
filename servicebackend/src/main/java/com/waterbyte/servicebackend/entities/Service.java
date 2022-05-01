package com.waterbyte.servicebackend.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Service {
    private int id;
    private String name;
    private int employeeId;
    private String address;
    private LocalDate date;
    private double latitude;
    private double longitude;
}
