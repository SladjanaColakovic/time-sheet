package com.example.timesheet.service;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.repository.IClientRepository;
import com.example.timesheet.core.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;
    @Autowired
    public ClientService(IClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public Client create(Client client) {
        return clientRepository.create(client);
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }
    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }
    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }
}
