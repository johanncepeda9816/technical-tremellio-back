package com.temelio.technical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temelio.technical.entities.Organization;
import com.temelio.technical.services.OrganizationServices;

@RestController
@RequestMapping("/api/v1/organizations")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizationController {

    @Autowired
    private OrganizationServices organizationServices;
    

    @RequestMapping(method = RequestMethod.POST, path = {"/save"})
    public ResponseEntity<?> saveOrganization(@RequestBody Organization organization){
        try {
            organizationServices.createOrganization(organization);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/get"})
    public ResponseEntity<?> getOrganizations(){
        try {
            return new ResponseEntity<>(organizationServices.getOrganizations(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
