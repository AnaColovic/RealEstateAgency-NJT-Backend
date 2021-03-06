/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.dto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Administrator
 */
public class LogInDTO implements Serializable{
    
    @NotBlank(message = "Username is required!")
    private String username;
    @NotBlank(message = "Username is required!")
    private String password;

    public LogInDTO() {
    }

    public LogInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
