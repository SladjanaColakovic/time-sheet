package com.example.timesheet.app.controller;



import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.ClientDTO;
import com.example.timesheet.app.dto.ClientUpdateDTO;
import com.example.timesheet.app.dto.Clients;
import com.example.timesheet.app.dto.NewClientDTO;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/client")
public class ClientController {

    private final IClientService clientService;

    private final CustomMapper mapper;

    @Autowired
    public ClientController(IClientService clientService, CustomMapper mapper){
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewClientDTO newClient){
        Client client = mapper.newClientDTOToClient(newClient);
        Client created = clientService.create(client);
        ClientDTO response = mapper.clientToClientDTO(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Client client = clientService.getById(id);
        ClientDTO response = mapper.clientToClientDTO(client);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Client> clients = clientService.getAll();
        List<ClientDTO> response = clients.stream()
                .map(mapper::clientToClientDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Clients(response), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ClientUpdateDTO editing){
        Client client = clientService.update(mapper.clientUpdateDTOToClient(editing));
        ClientDTO response = mapper.clientToClientDTO(client);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
