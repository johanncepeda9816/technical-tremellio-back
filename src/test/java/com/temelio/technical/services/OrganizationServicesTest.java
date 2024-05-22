package com.temelio.technical.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.temelio.technical.entities.Organization;
import com.temelio.technical.services.implementation.OrganizationsServicesImpl;

@WebMvcTest(OrganizationServices.class)
public class OrganizationServicesTest {

    @Test
    void testSaveOrganization() {

        OrganizationServices organizationServices = new OrganizationsServicesImpl();

        Organization requestDTO = new Organization();
        requestDTO.setId(1L);
        requestDTO.setCreatedAt(new Date().toString());

        organizationServices.createOrganization(requestDTO);
        assertEquals(1, organizationServices.getOrganizations().size());
    }

}
