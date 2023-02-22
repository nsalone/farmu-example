package com.farmu.example.common.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientReqTO {

    private String username;
    private String password;
    private Boolean enabled;
    private ClientType type;

}
