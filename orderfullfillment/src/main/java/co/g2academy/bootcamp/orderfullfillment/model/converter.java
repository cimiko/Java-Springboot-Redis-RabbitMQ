/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.orderfullfillment.model;

import co.g2academy.bootcamp.orderfullfillment.entity.Order;
import co.g2academy.bootcamp.orderfullfillment.entity.OrderItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cimiko
 */
public class converter {
    
    public Order convert(Checkout checkout){
        
        Order order = new Order();
        order.setStatus("RECEIVED");
        order.setUserName(checkout.getPerson().getName());
        order.setTransactionDate(new Date());
        List<OrderItem> orderItems = new ArrayList<>();
        order.setItems(orderItems);
        Integer value = 0;
        
        
        for(CheckoutItem item : checkout.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setQuantity(item.getQuantity());
            orderItems.add(orderItem);
            value += item.getPrice() * item.getQuantity();
            
        }
        order.setValue(value);
        return order;
    }
    
}
