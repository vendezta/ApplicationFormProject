package com.tanatat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBUtil {

	private static Logger log = Logger.getLogger(DBUtil.class);


	public static Connection getJndiDb() {
		Connection conn = null;

		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/jdbc_ApplicationForm");
			conn = ds.getConnection();
		} catch (SQLException e) {
			log.error("Error while getting connection : " + e.getMessage());
		} catch (Exception e) {
			log.error("Error while getting connection : " + e.getMessage());
		}

		return conn;
	}

	public static java.sql.Connection getJdbcConnection(String dbUrl, String dbUser, String dbPassword) {
		java.sql.Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (Exception e) {
			log.error("[ERROR] while getting jdbc connection [" + dbUrl + "] : " + e.getMessage());
		}

		return conn;
	}

	public static void closeDbConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			log.error("Error while closing database connection : " + e.getMessage());
		}
	}

	public static void closeStatement(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			log.error("Error while closing sql statement : " + e.getMessage());
		}
	}

	public static void closeResultSet(ResultSet rst) {
		try {
			if (rst != null) {
				rst.close();
				rst = null;
			}
		} catch (SQLException e) {
			log.error("Error while closing sql result set : " + e.getMessage());
		}
	}

	public static void rollbackDB(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			log.error("Error while rollback database connection : " + e.getMessage());
		}
	}
}
