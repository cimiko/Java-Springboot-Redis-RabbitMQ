/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service;

import co.g2academy.bootcamp.model.AddToCart;

/**
 *
 * @author cimiko
 */
public interface CheckoutService {
    
   public void addToCart(String userName,
            AddToCart addToCart);
   
   public void checkout(String userName);
    
}
