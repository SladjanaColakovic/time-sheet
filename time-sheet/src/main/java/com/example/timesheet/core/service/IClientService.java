package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Client;

public interface IClientService {
    Client create(Client client);
    Client getById(Long id);
}
