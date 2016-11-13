package main;

import java.sql.*;
import java.util.List;

public class DatabaseConnection {
	
	private static DatabaseConnection instance = null;
		
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://5.159.235.37:3306/car_rental";
	static final String USER = "car_rental";
	static final String PASS = "KLF.Tc8.OMc";
	private Connection conn;
	private Statement stmt;
	
	public static DatabaseConnection getInstance() {
      if(instance == null) {
         instance = new DatabaseConnection();
      }
      return instance;
    }
	
	public DatabaseConnection(){
		 try{
			Class.forName(JDBC_DRIVER);
		    System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Creating statement...");
		    stmt = conn.createStatement();
		 }catch(SQLException se){
		    System.out.println("SQL Exception");
		    se.printStackTrace();
		 }catch(Exception e){
		    e.printStackTrace();
		 }
	}

	public ResultSet selectData(String table, List<String> fields){
		ResultSet rs = null;
		try{
			String sql = "SELECT ";
			for (int i = 0; i < fields.size(); i++) {
				if(i == 0){
					sql += fields.get(i);
				} else {
					sql += ", " + fields.get(i);
				}
			}
			sql = sql + " FROM " + table; 
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateData(int id, String table, String field, String value){
		String sql = "UPDATE " + table + " SET " + field + "='" + value + "' WHERE id=" + id;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(String table,List<String> fields, List<String> values){
		try{
			String sql = "INSERT INTO " + table + " ( ";
			for (int i = 0; i < fields.size(); i++) {
				if(i == 0){
					sql += fields.get(i);
				} else {
					sql += ", " + fields.get(i);
				}
			}
			sql = sql + ") VALUES ( ";
			for (int i = 0; i < values.size(); i++) {
				if(i == 0){
					sql += "'" + values.get(i) + "'";
				} else {
					sql += ", '" + values.get(i) + "'";
				}
			}
			sql = sql + ") ";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}







