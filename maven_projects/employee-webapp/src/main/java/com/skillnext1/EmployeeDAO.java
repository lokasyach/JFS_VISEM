package com.skillnext1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmployeeDAO {

    public static void save(Employee emp) {

        String url = "jdbc:mysql://localhost:3306/employeedb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "8837";

        String sql = "INSERT INTO employee (name, email, salary) VALUES (?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setDouble(3, emp.getSalary());

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted = " + rows);

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace(); // DO NOT REMOVE
        }
    }
}
