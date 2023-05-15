package com.LightFeather.Supervisor.Module.controller;

import com.LightFeather.Supervisor.Module.model.NewNotification;
import com.LightFeather.Supervisor.Module.model.Supervisors;
import com.LightFeather.Supervisor.Module.service.SupervisorService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")


@RequiredArgsConstructor
public class SupervisorController {

        @Autowired
        private final SupervisorService supervisorService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/supervisors")
    public List<Supervisors> getSortedSupervisors(){

        return supervisorService.getAllSupervisors();

    }

    @PostMapping("/submit")
    public ResponseEntity<String> submit(@Valid @RequestBody NewNotification request) {
        // Validate request data
        if (request.getFirstName() == null || request.getFirstName().isEmpty()
                || !request.getFirstName().matches("^[a-zA-Z]+$")) {
            return ResponseEntity.badRequest().body("Invalid first name");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()
                || !request.getLastName().matches("^[a-zA-Z]+$")) {
            return ResponseEntity.badRequest().body("Invalid last name");
        }

        if (request.getSupervisor() == null || StringUtils.isEmpty(String.valueOf(request.getSupervisor()))) {
            return ResponseEntity.badRequest().body("Supervisor field is required");
        }

        if (request.getEmail() != null && !request.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        if (request.getPhoneNumber() != null && !request.getPhoneNumber().matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }
        // Check user preference and send notification accordingly
        if (request.isPreferEmail() && request.getEmail() != null) {
            System.out.println("Sending notification to email: " + request.isPreferEmail());
            // code to send notification to email
        } else if (!request.isPreferEmail() && request.getPhoneNumber() != null) {
            System.out.println("Sending notification to phone number: " + request.getPhoneNumber());
            // code to send notification to phone number
        } else {
            return ResponseEntity.badRequest().body("Invalid notification preference");
        }



        // Print notification data to console
        System.out.println("New Notification Request:");
        System.out.println("First Name: " + request.getFirstName());
        System.out.println("Last Name: " + request.getLastName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Phone Number: " + request.getPhoneNumber());
        System.out.println("Supervisor: " + request.getSupervisor());

        // Return success response
        return ResponseEntity.ok().build();
    }


}
