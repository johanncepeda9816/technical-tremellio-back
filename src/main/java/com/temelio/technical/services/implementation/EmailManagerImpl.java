package com.temelio.technical.services.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.temelio.technical.entities.Email;
import com.temelio.technical.services.EmailManagerServices;

@Component
public class EmailManagerImpl implements EmailManagerServices{

    private Map<String, Email> emailList = new HashMap<>();

    @Override
    public Boolean sendMassiveEmails(List<Email> emails) {
        try {
    
            emails.stream().forEach((item) -> {
                if (!item.getOrganization().isEmailSent()) {
                    item.setCreatedAt(new Date().toString());
                    item.getOrganization().setEmailSent(true);
                    emailList.put(item.getOrganization().getEmail(), item);
                }else{
                    System.out.println("Email was sent before");
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Email> getEmailList() {
        return emailList.values().stream().toList();
    }
    
}
