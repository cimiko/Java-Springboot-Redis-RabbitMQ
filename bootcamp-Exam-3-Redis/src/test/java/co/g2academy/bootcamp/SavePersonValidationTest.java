/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp;

import co.g2academy.bootcamp.entity.Person;
import co.g2academy.bootcamp.model.RegistrationValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author cimiko
 */
public class SavePersonValidationTest {
    
    private final RegistrationValidator validator = new RegistrationValidator();
    private final Person person;

    public SavePersonValidationTest() {
        person = new Person();
        person.setName("Hartono@mail.com");
        person.setPassword("tes123");
    }
    
    @Test
    public void testEmailValidateRegex(){
        Boolean actual = validator.validateEmail(person);
        assertTrue(actual);
    }
    
    @Test
    public void testEmailValidateNotUsingRegex(){
        Person invalidPerson = new Person();
        invalidPerson.setName("hartono");
        Boolean actual = validator.validateEmail(invalidPerson);
        assertFalse(actual);
    }
    
}
