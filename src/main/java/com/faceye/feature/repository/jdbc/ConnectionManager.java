package com.faceye.feature.repository.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionManager {
	private static Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	private static final String URL = "jdbc:mysql://10.12.12.143:33306/faceye_security?useEncode=true&characterEncoding=utf8";
	private static final String USER = "pro";
	private static final String PASSWORD = "pro";
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() {
		@Override
		protected Connection initialValue() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				logger.error(">>Exception:" + e);
			} catch (SQLException e) {
				logger.error(">>Exception:" + e);
			}
			return connection;
		}
	};

	public static Connection getConnection() {
		return threadLocal.get();
	}

	public static void close() {
		Connection conn = threadLocal.get();
		try {
			if (conn != null) {
				conn.close();
				threadLocal.remove();
			}
		} catch (SQLException e) {
			logger.error(">>Exception:" + e);
		}

	}
}
