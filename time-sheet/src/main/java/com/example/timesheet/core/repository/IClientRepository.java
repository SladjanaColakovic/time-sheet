package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Client;

public interface IClientRepository {
    Client create(Client client);
    Client getById(Long id);
}
