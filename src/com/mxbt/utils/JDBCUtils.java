package com.mxbt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//存放工具类的包
public class JDBCUtils {
	private static Connection connection = null;
	private static String url = "jdbc:mysql://139.129.58.244:3306/zzia_mxbt?&useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "root";
	
	public static Connection getConnection(){
		//第一步：加载数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//第二步：获取数据库连接
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(ResultSet resultSet, Statement statement,Connection connection){
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
