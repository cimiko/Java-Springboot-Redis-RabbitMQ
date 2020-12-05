/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp;

import co.g2academy.bootcamp.model.Register;
import co.g2academy.bootcamp.model.RegistrationValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author cimiko
 */
public class RegisterValidationTest {
    
    private final RegistrationValidator validator = new RegistrationValidator();
    private final Register model;
    private final Register invalidModel = new Register();

    public RegisterValidationTest() {
        model = new Register();
        model.setUserName("hartono@mail.com");
        model.setPassword("get123");
        model.setConfirmPassword("get123");
    }
    
    @Test
    public void testValidate(){
        Boolean actual = validator.validate(model);
        assertTrue(actual);
    }
    
    @Test
    public void testValidateUserNameAsEmailAddress(){
        Boolean actual = validator.validateUserNameAsEmailAddress(model);
        assertTrue(actual);
    }
    
    @Test
    public void testInvalidUserNameEmailAddress(){
        invalidModel.setUserName("hartono");
        Boolean actual = validator.validateUserNameAsEmailAddress(invalidModel);
        assertFalse(actual);
    }
    
    @Test
    public void testPasswordAndConfirmPasswordIsTheSame(){
        Boolean actual = validator.validatePasswordAndConfirmPasswordIsTheSame(model);
        assertTrue(actual);
    }
    
    @Test
    public void testPasswordAndConfirmPasswordIsNotTheSame(){
        invalidModel.setPassword("tes123");
        invalidModel.setConfirmPassword("get123");
        Boolean actual =  validator.validatePasswordAndConfirmPasswordIsTheSame(invalidModel);
        assertFalse(actual);
    }
    
}
