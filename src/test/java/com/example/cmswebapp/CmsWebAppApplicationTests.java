package com.example.cmswebapp;

import com.example.cmswebapp.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsWebAppApplicationTests {
	@Autowired
	private MainController controller;

	/**
	 * @throws Exception
	 * Application context test
	 */
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}
