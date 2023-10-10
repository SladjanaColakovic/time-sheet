package com.example.timesheet.app.controller;


import com.example.timesheet.app.dto.ClientDTO;
import com.example.timesheet.app.dto.NewClientDTO;
import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.service.IClientService;
import com.example.timesheet.core.service.ICountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Client client = clientService.getById(id);
        return new ResponseEntity<>(mapper.map(client, ClientDTO.class), HttpStatus.OK);

    }

}
