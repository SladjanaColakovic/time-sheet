package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Client;

import java.util.List;

public interface IClientService {
    Client create(Client client);
    Client getById(Long id);
    void delete(Long id);
    List<Client> getAll();
    Client update(Client client);
}
