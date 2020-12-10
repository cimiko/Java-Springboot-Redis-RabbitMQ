/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.controller;

import co.g2academy.bootcamp.Repository.BackgroundProcessRepository;
import co.g2academy.bootcamp.entity.Product;
import co.g2academy.bootcamp.Repository.ProductRepository;
import co.g2academy.bootcamp.Repository.CachedProductRepository;
import co.g2academy.bootcamp.entity.BackgroundProcess;
import co.g2academy.bootcamp.service.StockOpnameService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cimiko
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CachedProductRepository cachedProductRepository;

    @Autowired
    private BackgroundProcessRepository backgroundProcessRepository;

    @Autowired
    private StockOpnameService stockOpnameService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProductByCategory(
            @RequestParam String category,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort) {

        return buildResponseEntity(cachedProductRepository.findByCategory(category, page, size, sort));

    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getProductBySearchQuery(
            @RequestParam String query,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort
    ) {
        return buildResponseEntity(cachedProductRepository.findByNameContaining(query, page, size, sort));
    }

    private ResponseEntity<Map<String, Object>> buildResponseEntity(Page<Product> productPage) {
        Map<String, Object> response = new HashMap<>();
        response.put("product", productPage.getContent());
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return cachedProductRepository.findById(id);
    }

    @PostMapping("restricted/product")
    public String saveProduct(@RequestBody Product p) {
        productRepository.save(p);
        return "ok";
    }

    @PutMapping("restricted/product/{id}")
    public String updateProduct(@PathVariable Integer id,
            @RequestBody Product p) {
        productRepository.save(p);
        return "ok";
    }

    @GetMapping("/import")
    public String importProduct() {
        for (int i = 0; i < 1_000_000; i++) {
            Product p = new Product();
            p.setName("Product ini digenerate dari import url yang ke-" + i);
            p.setDescription("Product ini digenerate dari import url yang ke-" + i);
            p.setCategory("handphone");
            p.setPrice(i * 1000);
            p.setStock(i);
            productRepository.save(p);
        }
        return "ok";
    }

    @PostMapping("/restricted/stock-opname")
    public String stockOpname() {
        //1. check apakah stock opname process lagi jalan?
        BackgroundProcess stockOpnameProcess
                = backgroundProcessRepository.findByTypeAndStatus("STOCK_OPNAME", "ONPROGRESS");
        //2. iya? kembalikan status "Stock opname on progress xx%"
        if (stockOpnameProcess != null) {
            Double progress = stockOpnameProcess.getCurrentProgress().doubleValue()
                    * 100
                    / stockOpnameProcess.getMaximumValue().doubleValue();

            return "Stock opname on progress " + progress + "%";
            
        }
        //3. tidak or null, kembalikan status "OK"
        Long productCount = productRepository.count();
        stockOpnameProcess = new BackgroundProcess();
        stockOpnameProcess.setMaximumValue(productCount);
        stockOpnameProcess.setCurrentProgress(0L);
        stockOpnameProcess.setType("STOCK_OPNAME");
        stockOpnameProcess.setStatus("ONPROGRESS");
        backgroundProcessRepository.save(stockOpnameProcess);

        stockOpnameService.stockOpname();
        return "ok";
    }

}
