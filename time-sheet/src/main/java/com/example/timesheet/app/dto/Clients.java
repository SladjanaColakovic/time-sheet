package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Clients {
    private List<ClientDTO> clients;

    public Clients(List<ClientDTO> clients){
        this.clients = clients;
    }
}
