package com.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    // ================= CREATE =================
    public static void insertEmployee(String name, String dept, double salary) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "INSERT INTO employee(name, department, salary) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);

            ps.executeUpdate();
            System.out.println("Employee record inserted successfully");

        } catch (Exception e) {
            System.out.println("Employee insertion failed");
            e.printStackTrace();
        }
    }

    // ================= READ (ALL) =================
    public static void getAllEmployees() {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


System.out.println("ID\tName\tDepartment\tSalary");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("department") + "\t" +
                        rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            System.out.println("Failed to fetch employees");
            e.printStackTrace();
        }
    }

    // ================= READ (BY ID) =================
    public static void getEmployeeById(int id) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("department") + " " +
                        rs.getDouble("salary")
                );
            } else {
                System.out.println("Employee not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    public static void updateEmployee(int id, String name, String dept, double salary) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated successfully");
            } else {
                System.out.println("Employee not found");
            }

        } catch (Exception e) {
            System.out.println("Update failed");
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    public static void deleteEmployee(int id) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "DELETE FROM employee WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully");
            } else {
                System.out.println("Employee not found");
            }

        } catch (Exception e) {
            System.out.println("Delete failed");
            e.printStackTrace();
        }
    }
}


;

           

