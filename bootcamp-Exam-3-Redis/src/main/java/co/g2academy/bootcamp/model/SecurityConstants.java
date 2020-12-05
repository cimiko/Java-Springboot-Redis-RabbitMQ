/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.model;

/**
 *
 * @author cimiko
 */
public class SecurityConstants {
    
    public static final String SIGN_UP_URL = "/api/register";
    public static final String PRODUCT_CATEGORY_URL = "/api/products";
    public static final String PRODUCT_DETAIL_URL = "/api/product/**";
    public static final String SEARCH_URL = "/api/search";
    public static final String IMPORT_URL = "/api/import";
    
    public static final String KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7x!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+MbQeTh";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L * 60 * 30; //30 minutes JWT expiration
    
}
