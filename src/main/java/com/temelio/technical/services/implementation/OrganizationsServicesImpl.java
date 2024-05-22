package com.temelio.technical.services.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.temelio.technical.entities.Organization;
import com.temelio.technical.services.OrganizationServices;

@Component
public class OrganizationsServicesImpl implements OrganizationServices{

    private Map<String, Organization> organizations = new HashMap<String, Organization>();
    
    @Override
    public boolean createOrganization(Organization organization) {
        if(organizations.containsKey(organization.getEmail())) {
            return false;
        }else{
            organizations.put(organization.getEmail(), organization);
            return true;
        }
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizations.values().isEmpty() ? new ArrayList<Organization>() : new ArrayList<Organization>(organizations.values());
    }
}