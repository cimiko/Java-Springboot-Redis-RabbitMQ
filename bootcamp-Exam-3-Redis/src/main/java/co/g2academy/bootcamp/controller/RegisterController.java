/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.controller;

import co.g2academy.bootcamp.entity.Person;
import co.g2academy.bootcamp.model.Register;
import co.g2academy.bootcamp.model.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.g2academy.bootcamp.Repository.PersonRepository;

/**
 *
 * @author cimiko
 */
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private PersonRepository personService;

    @Autowired
    private RegistrationValidator validator;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")

    public String register(@RequestBody Register r) {
        //check if password and confirmPassword is the same
        if (validator.validate(r)) {
            //check if user is exist
            Person p = personService.findByName(r.getUserName());

            //if user not exist then save the data
            if (p == null) {
                Person newPerson = new Person();
                newPerson.setName(r.getUserName());
                newPerson.setPassword(bCryptPasswordEncoder.encode(r.getPassword()));
                personService.save(newPerson);
                return "Success";
            }
        }
        return "Failed";
    }

}
