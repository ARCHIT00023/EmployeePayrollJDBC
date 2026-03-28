package com.bridgelabz.employeepayroll;

import java.sql.*;

public class EmployeePayrollService {

    private static final String URL = "jdbc:mysql://localhost:3307/payroll_service";
    private static final String USER = "archit";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();
        service.readData();
    }

    public void readData() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee_payroll");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                Date start = rs.getDate("start");

                System.out.println(id + " | " + name + " | " + salary + " | " + start);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}