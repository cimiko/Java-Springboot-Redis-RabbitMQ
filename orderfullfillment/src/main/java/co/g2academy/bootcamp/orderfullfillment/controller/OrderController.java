/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.orderfullfillment.controller;

import co.g2academy.bootcamp.orderfullfillment.entity.Order;
import co.g2academy.bootcamp.orderfullfillment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cimiko
 */
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order-shipped")
    public String setStatusToShip(
            @RequestBody Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        if(order != null){
            order.setStatus("SHIPPED");
            orderRepository.save(order);
        }
        return "ok";  
    }

}
