package com.farmu.example.common.client.controller;

import com.farmu.example.common.client.dto.ClientReqTO;
import com.farmu.example.common.client.dto.ClientTO;
import com.farmu.example.common.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ClientTO create(
            @RequestBody final ClientReqTO request
    ) {
        log.debug("Request to create a new client with username '{}'", request.getUsername());
        final ClientTO response = service.save(request);
        log.debug("Response to create a new client with username '{}' OK", request.getUsername());
        return response;
    }

    @GetMapping
    public ClientTO findByUsername(
            @RequestParam("username") final String username
    ) {
        log.debug("Request to find client with username '{}'", username);
        final ClientTO response = service.findByUsername(username);
        log.debug("Response to find client with username '{}' OK", username);
        return response;
    }
}