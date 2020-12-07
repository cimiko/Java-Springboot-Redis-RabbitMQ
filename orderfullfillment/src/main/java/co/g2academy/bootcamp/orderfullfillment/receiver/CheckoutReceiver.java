/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.orderfullfillment.receiver;

import co.g2academy.bootcamp.orderfullfillment.entity.Order;
import co.g2academy.bootcamp.orderfullfillment.model.Checkout;
import co.g2academy.bootcamp.orderfullfillment.model.converter;
import co.g2academy.bootcamp.orderfullfillment.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author cimiko
 */
@RabbitListener(queues = "ecommerceq")
@Component
public class CheckoutReceiver {
    
    private static final Logger LOG = LoggerFactory.getLogger(CheckoutReceiver.class);
    
    @Autowired
    private OrderRepository orderRepository;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final converter converter = new converter();
    
    @RabbitHandler
    public void receive(byte[] message){
        try {
            String messageBody = new String (message);
            Checkout checkout = objectMapper.readValue(messageBody, Checkout.class);
            Order order = converter.convert(checkout);
            orderRepository.save(order);
        } catch (JsonProcessingException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
    
}
