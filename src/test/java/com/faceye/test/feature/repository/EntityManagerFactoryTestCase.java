package com.faceye.test.feature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.junit.Assert;

public class EntityManagerFactoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private EntityManagerFactory entityManagerFactory = null;
	@Autowired
	private DataSource dataSource = null;

	@Test
	public void entityManagerFactory() throws DataAccessException {
		Assert.assertTrue(entityManagerFactory != null);
	}

	@Test
	public void dataSource() throws Exception {
		Assert.assertTrue(dataSource != null);
	}

	@Test
	public void showDatabases() throws Exception {
		String sql = "show databases;";
		if (dataSource != null) {
			Connection conn = this.dataSource.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String db = rs.getString(1);
				logger.debug(">>FaceYe db is:" + db);
				Assert.assertTrue(StringUtils.isNotEmpty(db));

			}
			rs.close();
			stmt.close();
			conn.close();
		} else {
			Assert.assertTrue(1 > 2);
		}

	}
}
