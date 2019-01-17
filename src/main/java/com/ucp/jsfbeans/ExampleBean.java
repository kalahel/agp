package com.ucp.jsfbeans;

import lombok.Data;

import javax.annotation.ManagedBean;

@ManagedBean
@Data
public class ExampleBean {
    private String message = "No message specified";
    private String hello = "No Hello initialized";

    public String getMessage() {
        return(message);
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String sayHello(String arg) {
        String out = "<ul>\n";
        for (int i = 0; i < 10; i++)
            out += "<li>J'ai mangé " + i + " " + arg + "</li>\n";
        out += "</ul>";
        return out;
    }

    public String getHello() {
        return this.hello;
    }

    public void setHello(String arg) {
        String out = "<ul>\n";
        for (int i = 0; i < 10; i++)
            out += "<li>J'ai mangé " + i + " " + arg + "</li>\n";
        out += "</ul>";
        this.hello = out;
    }
}