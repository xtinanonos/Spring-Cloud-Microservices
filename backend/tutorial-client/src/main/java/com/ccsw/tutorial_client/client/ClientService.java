package com.ccsw.tutorial_client.client;

import com.ccsw.tutorial_client.client.model.Client;
import com.ccsw.tutorial_client.client.model.ClientDto;

import java.util.List;

public interface ClientService {

    /**
     * Recupera un {@link Client} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Client}
     */
    Client get(Long id);

    /**
     * Método para recuperar todas los {@link Client}
     *
     * @return {@link List} de {@link Client}
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar un {@link Client}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClientDto dto) throws Exception;

    /**
     * Método para borrar un {@link Client}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}
