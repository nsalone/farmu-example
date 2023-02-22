package com.farmu.example.common.client.store;

import com.farmu.example.common.client.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);

}
