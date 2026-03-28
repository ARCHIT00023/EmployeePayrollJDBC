package com.bridgelabz.employeepayroll;

import java.sql.*;

public class EmployeePayrollService {

    private static final String URL = "jdbc:mysql://localhost:3307/payroll_service";
    private static final String USER = "archit";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
    EmployeePayrollService service = new EmployeePayrollService();

    service.updateSalary("Terisa", 4000000);  // update again
    service.readData();
}

    // 🔥 UC3: Update Salary
    // 🔥 UC4: Using PreparedStatement
public void updateSalary(String name, double salary) {

    String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        // Set parameters
        ps.setDouble(1, salary);
        ps.setString(2, name);

        int rows = ps.executeUpdate();

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