package com.farmu.example.common.client.store;


import com.farmu.example.common.client.dto.Client;
import com.farmu.example.common.client.dto.ClientTO;
import com.farmu.example.common.store.Store;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.farmu.example.common.utils.ValidationUtils.checkArg;

@Component
@Transactional
public class ClientStore extends Store<Client, ClientTO> {

    @Autowired
    private ClientRepository repository;

    @Override
    protected JpaRepository<Client, Long> getRepository() {
        return this.repository;
    }

    @Override
    protected Class<Client> entityClass() {
        return Client.class;
    }

    @Override
    protected Class<ClientTO> dtoClass() {
        return ClientTO.class;
    }

    public Optional<ClientTO> findByUsername(final String user) {
        checkArg(user, "user");
        return this.map(repository.findByUsername(user));
    }

}