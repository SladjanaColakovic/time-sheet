package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.repository.IClientRepository;
import com.example.timesheet.data.entity.ClientEntity;
import com.example.timesheet.data.repository.ClientJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientJpa implements IClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    private final ModelMapper mapper;

    @Autowired
    public ClientJpa(ClientJpaRepository clientJpaRepository, ModelMapper mapper){
        this.clientJpaRepository = clientJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Client create(Client client) {
        ClientEntity newEntity = mapper.map(client, ClientEntity.class);
        ClientEntity saved = clientJpaRepository.save(newEntity);
        return mapper.map(saved, Client.class);
    }
    @Override
    public Client getById(Long id) {
        ClientEntity client = clientJpaRepository.findById(id).orElse(null);
        return mapper.map(client, Client.class);
    }
}
