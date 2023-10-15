package com.example.timesheet.service;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.example.timesheet.constants.CountryConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryTests {

    @Mock
    private ICountryRepository countryRepository;

    @Mock
    private Country country;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void testGetAll() {
        when(countryRepository.getAll()).thenReturn(Arrays.asList(new Country(DB_ID, DB_NAME)));

        List<Country> countries = countryService.getAll();

        assertEquals(1, countries.size());
        assertEquals(countries.get(0).getName(), DB_NAME);

        verify(countryRepository, times(1)).getAll();
        verifyNoMoreInteractions(countryRepository);
    }

    @Test
    public void testGetById() {
        when(countryRepository.getById(DB_ID)).thenReturn(country);

        Country dbCountry = countryService.getById(DB_ID);

        assertEquals(country, dbCountry);

        verify(countryRepository, times(1)).getById(DB_ID);
        verifyNoMoreInteractions(countryRepository);
    }

    @Test
    @Transactional
    public void testCreate() {

        Country newCountry = new Country();
        newCountry.setName(NEW_NAME);

        when(countryRepository.getAll()).thenReturn(Arrays.asList(new Country(DB_ID, DB_NAME)));
        when(countryRepository.create(newCountry)).thenReturn(newCountry);

        int dbSizeBeforeAdd = countryService.getAll().size();

        Country dbCountry = countryService.create(newCountry);

        when(countryRepository.getAll()).thenReturn(Arrays.asList(new Country(DB_ID, DB_NAME), newCountry));

        assertNotNull(dbCountry);

        List<Country> countries = countryService.getAll();
        assertEquals(dbSizeBeforeAdd + 1, countries.size());

        Country saved = countries.get(countries.size() - 1);

        assertEquals(saved.getName(), NEW_NAME);

        verify(countryRepository, times(2)).getAll();
        verify(countryRepository, times(1)).create(newCountry);
        verifyNoMoreInteractions(countryRepository);
    }

    @Test
    @Transactional
    public void testUpdate() {

        when(countryRepository.getById(DB_ID)).thenReturn(new Country(DB_ID, DB_NAME));

        Country countryForUpdate = countryService.getById(DB_ID);
        countryForUpdate.setName(NEW_NAME);

        when(countryRepository.create(countryForUpdate)).thenReturn(countryForUpdate);

        Country saved = countryService.create(countryForUpdate);

        assertNotNull(saved);

        Country dbCountry = countryService.getById(DB_ID);
        assertEquals(dbCountry.getName(), NEW_NAME);

        verify(countryRepository, times(2)).getById(DB_ID);
        verify(countryRepository, times(1)).create(countryForUpdate);
        verifyNoMoreInteractions(countryRepository);
    }

    @Test
    @Transactional
    public void testDelete() {
        when(countryRepository.getAll()).thenReturn(Arrays.asList(new Country(DB_ID, DB_NAME), new Country(DB_ID_TO_DELETE, DB_NAME_TO_DELETE)));
        doNothing().when(countryRepository).delete(DB_ID_TO_DELETE);
        when(countryRepository.getById(DB_ID_TO_DELETE)).thenReturn(null);

        int dbSizeBeforeDelete = countryService.getAll().size();
        countryService.delete(DB_ID_TO_DELETE);

        when(countryService.getAll()).thenReturn(Arrays.asList(new Country(DB_ID, DB_NAME)));

        List<Country> countries = countryService.getAll();
        assertEquals(dbSizeBeforeDelete -1, countries.size());

        Country dbCountry = countryService.getById(DB_ID_TO_DELETE);
        assertNull(dbCountry);

        verify(countryRepository, times(1)).delete(DB_ID_TO_DELETE);
        verify(countryRepository, times(2)).getAll();
        verify(countryRepository, times(1)).getById(DB_ID_TO_DELETE);
        verifyNoMoreInteractions(countryRepository);
    }

}
