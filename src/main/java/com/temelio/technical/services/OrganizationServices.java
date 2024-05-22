package com.temelio.technical.services;

import java.util.List;

import com.temelio.technical.entities.Organization;

public interface OrganizationServices {
    boolean createOrganization(Organization organization);
    List<Organization> getOrganizations();
}
