package com.bridgelabz.employeepayroll;

import java.sql.*;

public class EmployeePayrollService {

    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "archit";
    private static final String PASSWORD = "1234";

    // 🔥 UC12: Transaction Method
    public void performTransaction(Employee emp, double newSalary) {

        String insertQuery = "INSERT INTO employee_payroll (name, salary, start, gender) VALUES (?, ?, ?, ?)";
        String updateQuery = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 🔥 Start transaction
            con.setAutoCommit(false);

            // 🔹 INSERT
            PreparedStatement ps1 = con.prepareStatement(insertQuery);
            ps1.setString(1, emp.getName());
            ps1.setDouble(2, emp.getSalary());
            ps1.setDate(3, emp.getStartDate());
            ps1.setString(4, emp.getGender());
            ps1.executeUpdate();

            // 🔹 UPDATE
            PreparedStatement ps2 = con.prepareStatement(updateQuery);
            ps2.setDouble(1, newSalary);
            ps2.setString(2, emp.getName());
            ps2.executeUpdate();

            // ✅ Commit if all successful
            con.commit();

            System.out.println("✅ Transaction successful!");

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // ❗ rollback on error
                    System.out.println("❌ Transaction rolled back!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true); // restore default
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 🔹 Read Data (reuse)
    public void readData() {
        String query = "SELECT * FROM employee_payroll";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\nEmployee Data:");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("salary") + " | " +
                        rs.getDate("start") + " | " +
                        rs.getString("gender")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🚀 MAIN METHOD
    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        Employee emp = new Employee(
                "Alex",
                2000000,
                Date.valueOf("2023-05-01"),
                "M"
        );

        service.performTransaction(emp, 3500000);
        service.readData();
    }
}