package com.gokul.SpringBasicSecurityDemoPractice.controller;


import com.gokul.SpringBasicSecurityDemoPractice.model.AuthenticationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/health")
    public String healthcheck(){
        return "Healthy";
    }

    @GetMapping("/read")
    public String readData(){
        return "data read successful";
    }

    @PutMapping("/update")
    public String updateData(){
        return "updateSuccessful";
    }

    @PostMapping("/write")
    public String writeData(){
        return "writeSuccessful";
    }

    @DeleteMapping("/delete")
    public String deleteData(){
        return "deleteSuccessful";
    }


}
