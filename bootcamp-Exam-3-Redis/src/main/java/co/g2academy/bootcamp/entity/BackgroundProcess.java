/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.g2academy.bootcamp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cimiko
 */
@Entity
@Table(name = "T_BACKGROUND_PROCESS")
public class BackgroundProcess implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "TYPE", length = 20)
    private String type;
    
    @Column(name = "STATUS", length = 15)
    private String status;
    
    @Column(name = "CURRENT_PROGRESS")
    private Long currentProgress;
    
    @Column(name = "MAXIMUM_VALUE")
    private Long maximumValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(Long currentProgress) {
        this.currentProgress = currentProgress;
    }

    public Long getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(Long maximumValue) {
        this.maximumValue = maximumValue;
    }

    
    
}
