package com.example.timesheet.service;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.repository.IClientRepository;
import com.example.timesheet.core.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
