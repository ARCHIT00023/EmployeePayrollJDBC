package com.bridgelabz.employeepayroll;

import java.sql.*;

public class EmployeePayrollService {

    private static final String URL = "jdbc:mysql://localhost:3307/payroll_service";
    private static final String USER = "archit";
    private static final String PASSWORD = "1234";

    //main
  public static void main(String[] args) {
    EmployeePayrollService service = new EmployeePayrollService();

    Employee emp = new Employee(
            "David",
            3200000,
            Date.valueOf("2023-01-10"),
            "M"
    );

    service.addEmployeeObject(emp);
    service.readData();
}
// 🔥 UC11: Insert using Employee object
public void addEmployeeObject(Employee emp) {

    String query = "INSERT INTO employee_payroll (name, salary, start, gender) VALUES (?, ?, ?, ?)";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, emp.getName());
        ps.setDouble(2, emp.getSalary());
        ps.setDate(3, emp.getStartDate());
        ps.setString(4, emp.getGender());

        int rows = ps.executeUpdate();

        System.out.println("Inserted via object: " + rows);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// 🔥 UC10: Insert employee with full details
public void addEmployeeWithDetails(String name, double salary, String startDate, String gender) {

    String query = "INSERT INTO employee_payroll (name, salary, start, gender) VALUES (?, ?, ?, ?)";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, name);
        ps.setDouble(2, salary);
        ps.setDate(3, Date.valueOf(startDate)); // important
        ps.setString(4, gender);

        int rows = ps.executeUpdate();

        System.out.println("Employee inserted successfully! Rows: " + rows);

    } catch (SQLException e) {
        e.printStackTrace();
    }
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
// 🔥 UC5: Retrieve employees by date range
public void getEmployeesByDateRange(String startDate, String endDate) {

    String query = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        // Set date parameters
        ps.setDate(1, Date.valueOf(startDate));
        ps.setDate(2, Date.valueOf(endDate));

        ResultSet rs = ps.executeQuery();

        System.out.println("Employees between " + startDate + " and " + endDate + ":");

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
// 🔥 UC7: Insert new employee
public void addEmployee(String name, double salary, String startDate, String gender) {

    String query = "INSERT INTO employee_payroll (name, salary, start, gender) VALUES (?, ?, ?, ?)";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        // Set parameters
        ps.setString(1, name);
        ps.setDouble(2, salary);
        ps.setDate(3, Date.valueOf(startDate));
        ps.setString(4, gender);

        int rows = ps.executeUpdate();

        System.out.println("Inserted rows: " + rows);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// 🔥 UC8: Update salary using PreparedStatement
public void updateSalaryPrepared(String name, double salary) {

    String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        // set parameters
        ps.setDouble(1, salary);
        ps.setString(2, name);

        int rows = ps.executeUpdate();

        System.out.println("Updated rows: " + rows);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 // 🔥 UC9: Get employees after a given date
public void getEmployeesAfterDate(String date) {

    String query = "SELECT * FROM employee_payroll WHERE start >= ?";

    try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setDate(1, Date.valueOf(date));

        ResultSet rs = ps.executeQuery();

        System.out.println("Employees after " + date + ":");

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