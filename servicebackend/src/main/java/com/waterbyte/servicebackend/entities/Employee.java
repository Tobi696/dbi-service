package com.waterbyte.servicebackend.entities;

import lombok.Data;

@Data
public class Employee {
    public int id;
    public String firstName;
    public String lastName;
    public double latitude;
    public double longitude;
}
