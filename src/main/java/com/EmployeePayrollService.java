package src.main.java.com;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeePayrollService {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3307/payroll_service";
        String user = "root";
        String password = "your_password";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}