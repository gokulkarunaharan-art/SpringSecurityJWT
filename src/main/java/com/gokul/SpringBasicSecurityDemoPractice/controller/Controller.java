package com.gokul.SpringBasicSecurityDemoPractice.controller;


import com.gokul.SpringBasicSecurityDemoPractice.model.AuthenticationRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/health")
    public String healthcheck(){
        return "Healthy";
    }
}
