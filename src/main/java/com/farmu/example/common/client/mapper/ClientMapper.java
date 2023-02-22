package com.farmu.example.common.client.mapper;

import com.farmu.example.common.client.dto.ClientReqTO;
import com.farmu.example.common.client.dto.ClientTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.farmu.example.common.utils.ValidationUtils.checkArg;

@Service
@AllArgsConstructor
public class ClientMapper {

    private PasswordEncoder passEncoder;

    public ClientTO map(final ClientReqTO input) {
        checkArg(input, "input");
        final ClientTO output = new ClientTO();
        output.setUsername(input.getUsername());
        output.setPassword(passEncoder.encode(input.getPassword()));
        output.setEnabled(input.getEnabled());
        output.setType(input.getType());
        return output;
    }

}