package com.faceye.test.feature.controller;

import java.nio.charset.Charset;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Assert;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml", "classpath*:/applicationContext-web.xml" })
public class BaseControllerTestCase {
	protected MockMvc mockMvc;
	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Cookie cookie;
	protected HttpHeaders headers = new HttpHeaders();
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 //this.mockMvc =
		// MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(new
		// SessionFilter(), "/*").build();
		Assert.assertTrue(true);
	}
	@Test
	public void env() throws Exception{
		Assert.assertTrue(true);
	}
	
}
