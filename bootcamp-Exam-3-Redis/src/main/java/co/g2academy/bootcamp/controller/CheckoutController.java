/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.controller;

import co.g2academy.bootcamp.model.AddToCart;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.g2academy.bootcamp.service.CheckoutService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author cimiko
 */
@RestController
@RequestMapping("/api")
public class CheckoutController {
    
    @Autowired
    private CheckoutService checkoutService;
    
    
    @PostMapping("/add-to-cart")
    public String addToCart(
            @RequestBody AddToCart addToCart,
            Principal principal){
//        String userName = principal.getName();
        
        checkoutService.addToCart(getUserName(principal), addToCart);
        return "ok";
    }
    
    @PostMapping("/checkout")
    public String checkout(Principal principal){
        checkoutService.checkout(getUserName(principal));
        return "ok"; 
    }
    
    private String getUserName(Principal principal){
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
        Claims user = (Claims) token.getPrincipal();
        return user.getSubject();
    }
    
}
