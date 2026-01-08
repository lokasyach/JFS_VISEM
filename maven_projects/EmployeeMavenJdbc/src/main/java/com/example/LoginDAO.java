package com.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO 
{

    public static boolean validate(String username, String password) 
  {
        try (Connection con = DBUtil.getConnection()) 
    {

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();   // true if credentials match

        } 
    catch (Exception e) 
    {
            e.printStackTrace();
        }
        return false;
    }
}
