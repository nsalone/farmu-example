package com.farmu.example.common.client.service;

import com.farmu.example.common.client.dto.ClientReqTO;
import com.farmu.example.common.client.dto.ClientTO;
import com.farmu.example.common.client.mapper.ClientMapper;
import com.farmu.example.common.client.store.ClientStore;
import com.farmu.example.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.farmu.example.common.utils.ValidationUtils.checkArg;
import static java.lang.String.format;

@Service
public class ClientService {

    @Autowired
    private ClientStore store;
    @Autowired
    private ClientMapper mapper;

    public ClientTO save(final ClientReqTO request) {
        checkArg(request, "request");
        return store.save(mapper.map(request));
    }

    public ClientTO findByUsername(final String username) {
        checkArg(username, "username");
        return store.findByUsername(username).orElseThrow(() -> new NotFoundException(format("Client with username '%s' not found", username)));
    }

}