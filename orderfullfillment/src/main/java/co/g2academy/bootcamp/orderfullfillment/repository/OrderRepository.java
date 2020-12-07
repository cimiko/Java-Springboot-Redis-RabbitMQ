/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.orderfullfillment.repository;

import co.g2academy.bootcamp.orderfullfillment.entity.Order;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author cimiko
 */
public interface OrderRepository extends CrudRepository<Order, Integer>{
    
    
    
}
