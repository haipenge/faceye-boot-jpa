package com.faceye.test.feature.repository;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

@RunWith(JUnit4.class)
public class ObjTestCase {
	private Logger logger = LoggerFactory.getLogger(ObjTestCase.class);
	ObjA oa = null;
	ObjB ob = null;

	@Before
	public void set() throws Exception {
		oa = new ObjA();
		ob = new ObjB();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testThread() throws Exception {
		Thread oat = oa.getCurrentThread();
		Thread obt=oa.getCurrentThread();
		Assert.assertTrue(StringUtils.equals(oat.getName(), obt.getName()));
	}
}
