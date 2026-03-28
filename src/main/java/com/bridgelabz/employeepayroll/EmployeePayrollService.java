package com.bridgelabz.employeepayroll;

import java.sql.*;

public class EmployeePayrollService {

    private static final String URL = "jdbc:mysql://localhost:3307/payroll_service";
    private static final String USER = "archit";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();

        service.updateSalary("Terisa", 3500000);
        service.readData();
    }

    // 🔥 UC3: Update Salary
    public void updateSalary(String name, double salary) {
        String query = "UPDATE employee_payroll SET salary = " + salary + " WHERE name = '" + name + "'";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);

            System.out.println("Updated rows: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC2 reused
    public void readData() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee_payroll");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("salary") + " | " +
                        rs.getDate("start"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}