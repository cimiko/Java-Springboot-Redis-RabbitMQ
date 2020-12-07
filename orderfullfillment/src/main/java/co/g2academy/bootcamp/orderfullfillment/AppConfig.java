/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.orderfullfillment;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cimiko
 */
@Configuration
public class AppConfig {
    
//    @Bean
//    public Jackson2JsonMessageConverter getMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//        
//    }
    
//    @Bean
//    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(getMessageConverter());
//        return rabbitTemplate;
//    }
    
}
