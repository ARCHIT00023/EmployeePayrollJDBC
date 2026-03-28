package com.bridgelabz.employeepayroll;

import java.sql.Date;

public class Employee {

    private String name;
    private double salary;
    private Date startDate;
    private String gender;

    public Employee(String name, double salary, Date startDate, String gender) {
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
        this.gender = gender;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
    public Date getStartDate() { return startDate; }
    public String getGender() { return gender; }
}