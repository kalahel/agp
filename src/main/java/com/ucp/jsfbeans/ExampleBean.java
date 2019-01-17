package com.ucp.jsfbeans;

import lombok.Data;

import javax.annotation.ManagedBean;

@ManagedBean
@Data
public class ExampleBean {
    private String message = "No message specified";

    public String getMessage() {
        return(message);
    }
    public void setMessage(String message) {
        this.message = message;
    }
}