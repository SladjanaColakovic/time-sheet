package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFound;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.repository.IClientRepository;
import com.example.timesheet.data.entity.ClientEntity;
import com.example.timesheet.data.repository.ClientJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        if(client == null) throw new ObjectNotFound();
        return mapper.map(client, Client.class);
    }

    @Override
    public void delete(Long id) {
        ClientEntity client = clientJpaRepository.findById(id).orElse(null);
        if(client == null) throw new ObjectNotFound();
        clientJpaRepository.delete(client);
    }

    @Override
    public List<Client> getAll() {
        List<ClientEntity> clients = clientJpaRepository.findAll();
        return clients
                .stream()
                .map(element -> mapper.map(element, Client.class))
                .collect(Collectors.toList());
    }

    @Override
    public Client update(Client client) {
        ClientEntity editing = mapper.map(client, ClientEntity.class);
        ClientEntity saved = clientJpaRepository.save(editing);
        return mapper.map(saved, Client.class);
    }
}
