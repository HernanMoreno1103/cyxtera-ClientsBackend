package co.com.cyxtera.clients;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.cyxtera.clients.entities.Client;

/**
 * Class test
 * 
 * @author Hernan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientsBackendApplication.class)
@WebAppConfiguration
public class ClientsBackendApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientsBackendApplicationTests.class);

	private static final String URL_BASE = "/api/clients/";

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	private <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	/**
	 * Test rest service findAll
	 * 
	 * @throws Exception
	 */
	@Test
	public void findAllClients() throws Exception {
		LOGGER.info("----- Test findAllClients -----");

		String url = URL_BASE + "findAll";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();
		Client[] clients = mapFromJson(content, Client[].class);

		for (Client client : clients) {
			LOGGER.info(client.toString());
		}

		assertTrue(clients.length > 0);
	}

	/**
	 * Test rest service find
	 * 
	 * @throws Exception
	 */
	@Test
	public void findClientBySharedKey() throws Exception {
		LOGGER.info("----- Test findClientBySharedKey -----");

		String url = URL_BASE + "find?q=hmoreno";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();
		Client client = mapFromJson(content, Client.class);

		LOGGER.info(client.toString());

		assertNotNull(client);
	}

	/**
	 * Test rest service findById
	 * 
	 * @throws Exception
	 */
	@Test
	public void findClientById() throws Exception {
		LOGGER.info("----- Test findClientById -----");

		String url = URL_BASE + "findById/5";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();
		Client client = mapFromJson(content, Client.class);

		LOGGER.info(client.toString());

		assertNotNull(client);
	}

	/**
	 * Test rest service create
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveClient() throws Exception {
		LOGGER.info("----- Test saveClient -----");

		Client clientTest = new Client();
		clientTest.setName("Prueba Pruebas");
		clientTest.setPhone("123456789");
		clientTest.setEmail("prueba@prueba.com");
		clientTest.setStartDate(new Date());
		clientTest.setEndDate(new Date());

		String url = URL_BASE + "create";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapToJson(clientTest))).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();

		LOGGER.info(content);

		assertEquals(content, "Client is created successfully");
	}

	/**
	 * Test rest service delete
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteClient() throws Exception {
		LOGGER.info("----- Test deleteClient -----");

		String url = URL_BASE + "delete/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();

		LOGGER.info(content);

		assertEquals(content, "Client is deleted successfully");
	}
	
}
