package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.repository.IClientRepository;
import com.example.timesheet.data.entity.ClientEntity;
import com.example.timesheet.data.repository.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientRepository implements IClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    private final CustomMapper mapper;


    @Autowired
    public ClientRepository(ClientJpaRepository clientJpaRepository, CustomMapper mapper){
        this.clientJpaRepository = clientJpaRepository;
        this.mapper = mapper;

    }
    @Override
    public Client create(Client client) {
        ClientEntity newEntity = mapper.clientToClientEntity(client);
        ClientEntity saved = clientJpaRepository.save(newEntity);
        return mapper.clientEntityToClient(saved);
    }
    @Override
    public Client getById(Long id) {
        ClientEntity client = clientJpaRepository.findById(id).orElse(null);
        if(client == null) throw new ObjectNotFoundException();
        return mapper.clientEntityToClient(client);
    }

    @Override
    public void delete(Long id) {
        ClientEntity client = clientJpaRepository.findById(id).orElse(null);
        if(client == null) throw new ObjectNotFoundException();
        clientJpaRepository.delete(client);
    }

    @Override
    public List<Client> getAll() {
        List<ClientEntity> clients = clientJpaRepository.findAll();
        return clients
                .stream()
                .map(mapper::clientEntityToClient)
                .collect(Collectors.toList());
    }

    @Override
    public Client update(Client client) {
        ClientEntity editing = clientJpaRepository.findById(client.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.clientToClientEntityUpdate(client, editing);
        ClientEntity saved = clientJpaRepository.save(editing);
        return mapper.clientEntityToClient(saved);
    }
}
