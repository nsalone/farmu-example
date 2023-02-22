package com.farmu.example.common.client.dto;

import com.farmu.example.common.dto.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @Enumerated(STRING)
    @Column(name = "type")
    private ClientType type;

}