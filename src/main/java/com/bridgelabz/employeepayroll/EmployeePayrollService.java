package com.bridgelabz.employeepayroll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeePayrollService {

    // DB credentials 
    private static final String URL = "jdbc:mysql://localhost:3307/payroll_service";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();
        service.checkConnection();
    }

    // Method to check DB connection
    public void checkConnection() {
        try {
            // Step 1: Load Driver (optional for newer JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("✅ Connection Successful!");
            System.out.println("Connected to DB: " + connection.getCatalog());

            // Step 3: Close Connection
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed!");
            e.printStackTrace();
        }
    }
}