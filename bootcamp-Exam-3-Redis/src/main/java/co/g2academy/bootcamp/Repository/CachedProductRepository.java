/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.Repository;

import co.g2academy.bootcamp.entity.Product;
import co.g2academy.bootcamp.Repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

/**
 *
 * @author cimiko
 */
@Component
public class CachedProductRepository {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Cacheable(value = "findByCategory")
    public Page<Product> findByCategory(String category,
            Integer page,
            Integer size,
            String sort){
        return productRepository.findByCategory(category, buildPageable(page, size, sort));
    }
    
    @Cacheable(value = "findByNameContaining")
    public Page<Product> findByNameContaining(String query,
            Integer page,
            Integer size,
            String sort){
        return productRepository.findByNameContaining(query, buildPageable(page, size, sort));
    }
    
    private Pageable buildPageable(Integer page, Integer size, String sort){
        Sort.Order order = null;
        if ("cheapest".equals(sort)) {
            order = new Sort.Order(Sort.Direction.ASC, "price");
        } else if ("expensive".equals(sort)) {
            order = new Sort.Order(Sort.Direction.DESC, "price");
        } else if ("alphabetical".equals(sort)) {
            order = new Sort.Order(Sort.Direction.ASC, "name");
        }

        return PageRequest.of(page, size, Sort.by(order));
    }
    
    @Cacheable("findById")
    public Product findById(Integer id) {
        Optional<Product> optional =  productRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    
}
