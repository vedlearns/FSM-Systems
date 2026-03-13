package com.hackerrank.stereotypes.service;

import com.hackerrank.stereotypes.model.Person;
import com.hackerrank.stereotypes.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
   private final  ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Person retrieve(Integer id){
        return contactRepository.findById(id).get();
    }

    public Person save(Person person){
        Person p = new Person();
        p.setName(person.getName());
        p.setMobile(person.getMobile());
        return contactRepository.save(p);
    }
}
