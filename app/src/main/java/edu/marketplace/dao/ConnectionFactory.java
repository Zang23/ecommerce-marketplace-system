package edu.marketplace.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionFactory {

  private static final String DB_JDBC_URI = 
  "jdbc:sqlserver://localhost:1433;databaseName=MarketPlace;encrypt=false;trustServerCertificate=true";
  
  private static final String DB_USER = "sa";
  private static final String DB_PASS = "123Elkt!";
  

  public static Connection getConnection() throws SQLException{
      
    return DriverManager.getConnection(
      DB_JDBC_URI,
      DB_USER,
      DB_PASS
    );

  }

  
}
