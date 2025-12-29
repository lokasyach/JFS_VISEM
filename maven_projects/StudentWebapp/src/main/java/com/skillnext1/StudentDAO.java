package com.skillnext1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.skillnext1.Student;

public class StudentDAO {

    public static void save(Student std) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db",
                "root",
                "8837"
            );

            String sql = "INSERT INTO student(name,sem,dept) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, std.getName());
            ps.setInt(2, std.getSem());
            ps.setString(3, std.getDept());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
