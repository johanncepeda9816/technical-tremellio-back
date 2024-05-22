package com.temelio.technical.services;

import java.util.List;

import com.temelio.technical.entities.Email;

public interface EmailManagerServices {
    Boolean sendMassiveEmails(Email organization);
    List<Email> getEmailList();
}
