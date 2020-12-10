/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author cimiko
 */
@SpringBootApplication(scanBasePackages = {"co.g2academy.bootcamp"})
@EnableCaching
@EnableAsync
public class BootcampExam3Application {

    public static void main(String[] args) {
        SpringApplication.run(BootcampExam3Application.class, args);
    }

}
