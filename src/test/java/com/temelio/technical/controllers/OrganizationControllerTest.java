package com.temelio.technical.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temelio.technical.entities.Organization;
import com.temelio.technical.services.OrganizationServices;

@WebMvcTest(OrganizationController.class)
public class OrganizationControllerTest {

	@MockBean
	private OrganizationServices organizationServices;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testSaveOrganization() throws Exception {

		when(organizationServices.createOrganization(any(Organization.class))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/organizations/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(Organization.builder().build())))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		verify(organizationServices, times(1)).createOrganization(any(Organization.class));
	}

	@Test
	void testGetOrganizations() throws Exception {

		List<Organization> organizations = Arrays.asList(
				Organization.builder().id(1L).name("Org1").build(),
				Organization.builder().id(2L).name("Org2").build());

		when(organizationServices.getOrganizations()).thenReturn(organizations);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/organizations/list"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Org1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Org2"));

		verify(organizationServices, times(1)).getOrganizations();
	}
}
