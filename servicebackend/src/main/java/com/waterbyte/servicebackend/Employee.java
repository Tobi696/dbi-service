package com.waterbyte.servicebackend;

import lombok.Data;

@Data
public class Employee {
    public Integer id;
    public String firstName;
    public String lastName;
    public double latitude;
    public double longitude;
}
