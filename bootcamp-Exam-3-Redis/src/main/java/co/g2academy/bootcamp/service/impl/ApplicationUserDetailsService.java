/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service.impl;

import co.g2academy.bootcamp.entity.Person;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import co.g2academy.bootcamp.Repository.PersonRepository;

/**
 *
 * @author cimiko
 */
@Service
public class ApplicationUserDetailsService implements UserDetailsService{
    
    @Autowired
    private PersonRepository personService;
    
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Person person = personService.findByName(userName);
        if(person == null){
            throw new UsernameNotFoundException(userName);
        }
        return new User(person.getName(), person.getPassword(), Collections.emptyList());
    }
    
}
