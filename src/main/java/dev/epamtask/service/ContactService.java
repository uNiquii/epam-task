package dev.epamtask.service;

import dev.epamtask.exception.NotFoundException;
import dev.epamtask.model.Contact;
import dev.epamtask.model.User;
import dev.epamtask.parser.ParseDate;
import dev.epamtask.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class ContactService {
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    @Autowired
   ContactRepository contactRepository;


    public void createContact(Contact contact) {


        int result = contactRepository.create(contact);
        try {
            if (result == 1) {
                log.info("New contact created ");
            } else {
                throw new IllegalStateException("oops could not delete contact");
            }
        } catch (IllegalStateException exception) {
            log.info(exception.toString());
        }
    }
}
