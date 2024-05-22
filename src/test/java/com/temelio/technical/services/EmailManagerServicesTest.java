package com.temelio.technical.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.temelio.technical.entities.Email;
import com.temelio.technical.entities.Organization;
import com.temelio.technical.services.implementation.EmailManagerImpl;

public class EmailManagerServicesTest {

    @Test
    public void testSendMassiveEmails() {

        List<Email> emails = new ArrayList<>();
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().build()));
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().build()));
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().build()));

        EmailManagerServices emailServices = new EmailManagerImpl();
        Boolean result = emailServices.sendMassiveEmails(emails);

        assertTrue(result);
    }

    @Test
    public void testGetEmailList() {
        List<Email> emails = new ArrayList<>();
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().email("1@mail.com").build()));
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().email("2@mail.com").build()));
        emails.add(new Email(new Date().toString(), "<p></p>", Organization.builder().email("3@mail.com").build()));

        EmailManagerServices emailServices = new EmailManagerImpl();
        emailServices.sendMassiveEmails(emails);

        List<Email> emailList = emailServices.getEmailList();

        assertEquals(emails.size(), emailList.size());

    }
}
