package com.example;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil
{

    public static Connection getConnection() throws Exception 
  {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/skillnext_db",
            "root",
            "8837"
        );
    }
}

