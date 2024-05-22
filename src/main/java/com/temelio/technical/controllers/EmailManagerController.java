package com.temelio.technical.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.temelio.technical.entities.Email;
import com.temelio.technical.entities.ErrorResponse;
import com.temelio.technical.services.EmailManagerServices;

@RestController
@RequestMapping("/api/v1/email-manager")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailManagerController {
    @Autowired
    EmailManagerServices emailManagerServices;

      @RequestMapping(method = RequestMethod.POST, path = {"/send"})
    public ResponseEntity<?> sendMassiveEmails(@RequestBody Email organization) {
        try {
            Boolean created = emailManagerServices.sendMassiveEmails(organization);
            if (created) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ErrorResponse("Organization already exists"), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse("Internal Server Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list"})
    public ResponseEntity<?> getEmailList() {
        try {
            return new ResponseEntity<>(emailManagerServices.getEmailList(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse("Internal Server Error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
