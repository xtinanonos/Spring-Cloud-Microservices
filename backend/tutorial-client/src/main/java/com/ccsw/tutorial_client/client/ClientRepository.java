package com.ccsw.tutorial_client.client;

import com.ccsw.tutorial_client.client.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    boolean existsByName(String name);
}
