/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.service.impl;

import co.g2academy.bootcamp.Repository.BackgroundProcessRepository;
import co.g2academy.bootcamp.Repository.ProductRepository;
import co.g2academy.bootcamp.entity.BackgroundProcess;
import co.g2academy.bootcamp.entity.Product;
import co.g2academy.bootcamp.service.StockOpnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author cimiko
 */
@Service
public class StockOpnameServiceImpl implements StockOpnameService{
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BackgroundProcessRepository backgroundProcessRepository;
    
    @Override
    @Async
    public void stockOpname(){
        Iterable<Product> products = productRepository.findAll();
        for(Product p : products){
            //stock opname dummy process! update stock product by 1
            p.setStock(p.getStock() + 1);
            productRepository.save(p);
            //increase current process by 1 in background process
            BackgroundProcess stockOpname = 
                    backgroundProcessRepository.findByTypeAndStatus("STOCK_OPNAME", "ONPROGRESS");
            
            if(stockOpname != null){
                stockOpname.setCurrentProgress(stockOpname.getCurrentProgress() + 1);
                backgroundProcessRepository.save(stockOpname);
            }
        }
    }
    
}
