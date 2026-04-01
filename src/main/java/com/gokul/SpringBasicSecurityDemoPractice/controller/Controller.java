package com.gokul.SpringBasicSecurityDemoPractice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/health")
    public String healthcheck() {
        return "Healthy";
    }

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('READ')")
    public String readData() {
        return "data read successful";
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('UPDATE')")
    public String updateData() {
        return "updateSuccessful";
    }

    @PostMapping("/write")
    @PreAuthorize("hasAuthority('WRITE')")
    public String writeData() {
        return "writeSuccessful";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public String deleteData() {
        return "deleteSuccessful";
    }

}
