package com.faceye.test.feature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

import com.faceye.feature.repository.jdbc.ConnectionManager;

@RunWith(JUnit4.class)
public class ConnectionManagerTestCase {
	private Logger logger = LoggerFactory.getLogger(ConnectionManagerTestCase.class);
	Connection conn = null;
	private static final String URL = "jdbc:mysql://10.12.12.143:33306/faceye_security?useEncode=true&characterEncoding=utf8&useSSL=false";
	private static final String USER = "pro";
	private static final String PASSWORD = "pro";

	@Before
	public void set() throws Exception {
//		conn = ConnectionManager.getConnection();
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	@Test
	public void testGetConnection() throws Exception{
		Assert.assertTrue(conn!=null);
	}

	@Test
	public void testQueryTable() throws Exception {
		String column1Value = "";
		String sql = "select * from sec_operation_address";
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next()) {
			column1Value = rs.getString(1);
			logger.debug(">>" + rs.getString(1));
			break;
		}
		Assert.assertTrue(StringUtils.isNotEmpty(column1Value));
	}
	@Test
	public void testShowDatabases() throws Exception{
		String sql="select * from aa;";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		while(rs.next()){
		   String col=rs.getString(1);
		   logger.debug("Col: "+col);
		}
		Assert.assertTrue(rs!=null);
	}
	

	@After
	public void after() throws Exception {
		ConnectionManager.close();
	}
}
