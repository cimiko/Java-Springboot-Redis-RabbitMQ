/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.Repository;

import co.g2academy.bootcamp.entity.Checkout;
import co.g2academy.bootcamp.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cimiko
 */
@Repository
public interface CheckoutRepository extends CrudRepository<Checkout, Integer>{

    Checkout findByPersonAndStatus(Person person, String status);
    
}
