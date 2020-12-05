/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.Repository;

import co.g2academy.bootcamp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cimiko
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
    
    Page<Product> findByCategory(String category, Pageable pageable);
    
    Page<Product> findByNameContaining(String query, Pageable pageable);
}
