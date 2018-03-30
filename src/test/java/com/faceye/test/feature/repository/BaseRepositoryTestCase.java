package com.faceye.test.feature.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

import com.faceye.feature.Application;


/**
 * 基础测试用例
 * 
 * @author:haipenge
 * @Create Date:2014年5月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Rollback(true)
//@ContextConfiguration(locations={"classpath*:/applicationContext.xml"})
//extends AbstractTransactionalJUnit4SpringContextTests
abstract public class BaseRepositoryTestCase {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testEnv() throws Exception {
		Assert.assertTrue(true);
	}

}
