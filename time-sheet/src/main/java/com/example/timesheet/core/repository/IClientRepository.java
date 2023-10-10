package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Client;

import java.util.List;

public interface IClientRepository {
    Client create(Client client);
    Client getById(Long id);
    void delete(Long id);
    List<Client> getAll();
    Client update(Client client);
}
