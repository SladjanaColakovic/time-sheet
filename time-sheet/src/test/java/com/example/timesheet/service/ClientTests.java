package com.example.timesheet.service;

import com.example.timesheet.core.model.Client;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.IClientRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.ClientConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTests {

    @Mock
    private IClientRepository clientRepository;

    @Mock
    Client client;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testGetAll() {
        when(clientRepository.getAll()).thenReturn(Arrays.asList(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME))));

        List<Client> clients = clientService.getAll();

        assertEquals(1, clients.size());
        assertEquals(clients.get(0).getName(), DB_NAME);

        verify(clientRepository, times(1)).getAll();
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void testGetById() {
        when(clientRepository.getById(DB_ID)).thenReturn(client);

        Client dbClient = clientService.getById(DB_ID);

        assertEquals(client, dbClient);

        verify(clientRepository, times(1)).getById(DB_ID);
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    @Transactional
    public void testCreate() {

        Client newClient = new Client(NEW_NAME, NEW_ADDRESS, NEW_CITY, NEW_POSTAL_CODE, new Country(NEW_COUNTRY_ID, NEW_COUNTRY_NAME));


        when(clientRepository.getAll()).thenReturn(Arrays.asList(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME))));
        when(clientRepository.create(newClient)).thenReturn(newClient);

        int dbSizeBeforeAdd = clientService.getAll().size();

        Client dbClient = clientService.create(newClient);

        when(clientRepository.getAll()).thenReturn(Arrays.asList(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME)), newClient));

        assertNotNull(dbClient);

        List<Client> clients = clientService.getAll();
        assertEquals(dbSizeBeforeAdd + 1, clients.size());

        Client saved = clients.get(clients.size() - 1);

        assertEquals(saved.getName(), NEW_NAME);

        verify(clientRepository, times(2)).getAll();
        verify(clientRepository, times(1)).create(newClient);
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    @Transactional
    public void testUpdate() {

        when(clientRepository.getById(DB_ID)).thenReturn(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME)));

        Client clientForUpdate = clientService.getById(DB_ID);
        clientForUpdate.setName(NEW_NAME);

        when(clientRepository.create(clientForUpdate)).thenReturn(clientForUpdate);

        Client saved = clientService.create(clientForUpdate);

        assertNotNull(saved);

        Client dbClient = clientService.getById(DB_ID);
        assertEquals(dbClient.getName(), NEW_NAME);

        verify(clientRepository, times(2)).getById(DB_ID);
        verify(clientRepository, times(1)).create(clientForUpdate);
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    @Transactional
    public void testDelete() {
        when(clientRepository.getAll()).thenReturn(Arrays.asList(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME)), new Client(DB_ID_TO_DELETE, DB_NAME_TO_DELETE, DB_ADDRESS_TO_DELETE, DB_CITY_TO_DELETE, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID_TO_DELETE, DB_COUNTRY_NAME_TO_DELETE))));
        doNothing().when(clientRepository).delete(DB_ID_TO_DELETE);
        when(clientRepository.getById(DB_ID_TO_DELETE)).thenReturn(null);

        int dbSizeBeforeDelete = clientService.getAll().size();
        clientService.delete(DB_ID_TO_DELETE);

        when(clientService.getAll()).thenReturn(Arrays.asList(new Client(DB_ID, DB_NAME, DB_ADDRESS, DB_CITY, DB_POSTAL_CODE, new Country(DB_COUNTRY_ID, DB_COUNTRY_NAME))));

        List<Client> clients = clientService.getAll();
        assertEquals(dbSizeBeforeDelete -1, clients.size());

        Client dbClient = clientService.getById(DB_ID_TO_DELETE);
        assertNull(dbClient);

        verify(clientRepository, times(1)).delete(DB_ID_TO_DELETE);
        verify(clientRepository, times(2)).getAll();
        verify(clientRepository, times(1)).getById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(clientRepository);
    }
}
