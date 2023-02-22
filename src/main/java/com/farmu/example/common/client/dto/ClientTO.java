package com.farmu.example.common.client.dto;

import com.farmu.example.common.dto.entity.BaseEntityTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientTO extends BaseEntityTO {

    private String username;
    private String password;
    private Boolean enabled;
    private ClientType type;

}