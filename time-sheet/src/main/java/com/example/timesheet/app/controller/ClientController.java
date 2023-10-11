package com.example.timesheet.app.controller;



import com.example.timesheet.app.dto.ClientDTO;
import com.example.timesheet.app.dto.ClientUpdateDTO;
import com.example.timesheet.app.dto.NewClientDTO;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.IClientService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
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

    private final ModelMapper mapper;

    @Autowired
    public ClientController(IClientService clientService, ModelMapper mapper){
        this.clientService = clientService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewClientDTO newClient){
        Client client = mapper.map(newClient, Client.class);
        Client created = clientService.create(client);
        return new ResponseEntity<>(mapper.map(created, ClientDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Client client = clientService.getById(id);
        return new ResponseEntity<>(mapper.map(client, ClientDTO.class), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Client> clients = clientService.getAll();
        return new ResponseEntity<>(clients.stream()
                .map(element -> mapper.map(element, ClientDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ClientUpdateDTO editing){
        Client client = clientService.update(mapper.map(editing, Client.class));
        return new ResponseEntity<>(mapper.map(client, ClientDTO.class), HttpStatus.OK);
    }

}
