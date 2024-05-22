package com.temelio.technical.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.temelio.technical.entities.Email;

import com.temelio.technical.services.EmailManagerServices;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(EmailManagerController.class)
public class EmailManagerControllerTest {

    @MockBean
    private EmailManagerServices emailManagerServices;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSendMassiveEmails() throws Exception {
        List<Email> emails = new ArrayList<>();

        when(emailManagerServices.sendMassiveEmails(emails)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/email-manager/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(emails)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(emailManagerServices, times(1)).sendMassiveEmails(emails);
    }

    @Test
    public void testGetEmailList() throws Exception {
        List<Email> emails = new ArrayList<>(
                List.of(
                        new Email(),
                        new Email()));

        emailManagerServices.sendMassiveEmails(emails);

        when(emailManagerServices.getEmailList()).thenReturn(emails);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/email-manager/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(emailManagerServices, times(1)).sendMassiveEmails(emails);
        assertEquals(emails.size(), 2);

    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
