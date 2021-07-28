package com.maclacerda.aluraflix.controllers;

import java.net.URI;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.maclacerda.aluraflix.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CategoriesControllerTest {
	
	private MockHttpServletRequestBuilder request;
	private ResultMatcher expectedResult;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MockMvc mock;

	@Before
	public void setUp() {
		Category category1 = new Category("Free", "#FF0000");
		Category category2 = new Category("Front-End", "#FF00FF");

		entityManager.persist(category1);
		entityManager.persist(category2);
	}

	@Test
	public void testList() throws Exception {
		URI path = new URI("/categories");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDetail() throws Exception {
		URI path = new URI("/categories/1");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDetailWithWrongId() throws Exception {
		URI path = new URI("/categories/99");

		request = MockMvcRequestBuilders.get(path);
		expectedResult = MockMvcResultMatchers.status().isNotFound();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testAdd() throws Exception {
		URI path = new URI("/categories");
		String json = "{\"title\": \"Back-end\", \"color\": \"#FEFEFE\"}";

		request = MockMvcRequestBuilders.post(path).content(json).header("Content-Type", "application/json");
		expectedResult = MockMvcResultMatchers.status().isCreated();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testUpdate() throws Exception {
		URI path = new URI("/categories/1");
		String json = "{\"title\": \"Free Up\"}";

		request = MockMvcRequestBuilders.put(path).content(json).header("Content-Type", "application/json");
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDelete() throws Exception {
		URI path = new URI("/categories/1");

		request = MockMvcRequestBuilders.delete(path);
		expectedResult = MockMvcResultMatchers.status().isOk();

		mock.perform(request).andExpect(expectedResult);
	}

	@Test
	public void testDeleteWithWrongId() throws Exception {
		URI path = new URI("/categories/99");

		request = MockMvcRequestBuilders.delete(path);
		expectedResult = MockMvcResultMatchers.status().isNotFound();

		mock.perform(request).andExpect(expectedResult);
	}

}
