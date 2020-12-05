/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service.impl;

import co.g2academy.bootcamp.entity.Checkout;
import co.g2academy.bootcamp.entity.CheckoutItem;
import co.g2academy.bootcamp.entity.Person;
import co.g2academy.bootcamp.entity.Product;
import co.g2academy.bootcamp.model.AddToCart;
import co.g2academy.bootcamp.Repository.CheckoutRepository;
import co.g2academy.bootcamp.service.CheckoutService;
import co.g2academy.bootcamp.Repository.PersonRepository;
import co.g2academy.bootcamp.Repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cimiko
 */
@Service
public class CheckoutServiceImpl implements CheckoutService{
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private CheckoutRepository checkoutRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Override
    @Transactional
    public void addToCart(String userName, AddToCart addToCart){
        Person person = personRepository.findByName(userName);
        
        //get checkout data that has status = Active
        Checkout checkout = 
                checkoutRepository.findByPersonAndStatus(person, "ACTIVE");
        
        if(checkout == null){
            checkout = new Checkout();
            checkout.setPerson(person);
            checkout.setStatus("ACTIVE");
            List<CheckoutItem> items = new ArrayList<>();
            checkout.setItems(items);
        }
        Product product = 
                productRepository.findById(addToCart.getProductId()).get();
        
        if(product != null){
            CheckoutItem item = new CheckoutItem();
            item.setCheckout(checkout);
            item.setPrice(product.getPrice());
            item.setQuantity(addToCart.getQuantity());
            item.setProduct(product);
            checkout.getItems().add(item);
        }
        checkoutRepository.save(checkout);
    }
    
    @Override
    @Transactional
    public void checkout(String userName){
        Person person = personRepository.findByName(userName);
        Checkout checkout =
                checkoutRepository.findByPersonAndStatus(person, "ACTIVE");
        if(checkout != null){
            checkout.setStatus("PROCESSED");
            //send message to rabbit MQ
            rabbitTemplate.convertAndSend(checkout);
            //save to database after changing status
            checkoutRepository.save(checkout);
        }
    }
}
