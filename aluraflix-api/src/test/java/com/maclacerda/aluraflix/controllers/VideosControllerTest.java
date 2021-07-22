package com.maclacerda.aluraflix.controllers;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.maclacerda.aluraflix.models.Video;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
public class VideosControllerTest {

	private MockHttpServletRequestBuilder request;
	private ResultMatcher expectedResult;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MockMvc mock;

	private void populateDatabase() {
		Video video = new Video("Video 1", "Add first video in database", "http://www.google.com/video_1");

		entityManager.persist(video);
	}

	@Test
	public void testList() throws Exception {
		populateDatabase();

		URI path = new URI("/videos");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDetail() throws Exception {
		populateDatabase();

		URI path = new URI("/videos/1");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDetailWithWrongId() throws Exception {
		URI path = new URI("/videos/1");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isNotFound();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testAdd() throws Exception {
		URI path = new URI("/videos");
		String json = "{\"title\": \"Video 1\", \"description\": \"Description for 1\", \"url\": \"http://www.google.com\"}";

		request = MockMvcRequestBuilders.post(path).content(json).header("Content-Type", "application/json");
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testUpdate() throws Exception {
		populateDatabase();

		URI path = new URI("/videos/1");
		String json = "{\"title\": \"Video Up 1\", \"description\": \"Description Up for 1\", \"url\": \"http://www.google.com/video_1\"}";

		request = MockMvcRequestBuilders.put(path).content(json).header("Content-Type", "application/json");
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDelete() throws Exception {
		populateDatabase();

		URI path = new URI("/videos/1");

		request = MockMvcRequestBuilders.delete(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDeleteWithWrongId() throws Exception {
		URI path = new URI("/videos/99");

		request = MockMvcRequestBuilders.delete(path);
		expectedResult = MockMvcResultMatchers.status().isNotFound();

		mock.perform(request).andExpect(expectedResult);
	}

}
