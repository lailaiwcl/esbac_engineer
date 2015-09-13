package org.lailaiwcl.esbac.common;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBHelper {
	private static ComboPooledDataSource ds = null;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/esbac_exp";
	private static String user = "root";
	private static String pwd = "root";
	static {
		ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(driver);
		} catch (PropertyVetoException e) {
		}
		ds.setJdbcUrl(url);
		ds.setUser(user);
		ds.setPassword(pwd);
		ds.setInitialPoolSize(10);
		ds.setMaxPoolSize(20);
		ds.setAcquireIncrement(1);
		ds.setAutoCommitOnClose(true);
		ds.setBreakAfterAcquireFailure(true);
	}

	/**
	 * 通过c3p0连接池来获取连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		getConnection();
	}

	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
