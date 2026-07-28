package com.cognizant.orm_learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.orm_learn.exception.CountryNotFoundException;
import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country findCountryByCode(String code) throws CountryNotFoundException {

        Optional<Country> country = countryRepository.findById(code);

        if (country.isPresent()) {
            return country.get();
        } else {
            throw new CountryNotFoundException("Country not found with code : " + code);
        }
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name)
            throws CountryNotFoundException {

        Country country = findCountryByCode(code);

        country.setName(name);

        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Search by partial name
    @Transactional
    public List<Country> searchCountries(String text) {
        return countryRepository.findByNameContainingIgnoreCase(text);
    }

    // Search by partial name in ascending order
    @Transactional
    public List<Country> searchCountriesSorted(String text) {
        return countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc(text);
    }

    // Search by starting letter
    @Transactional
    public List<Country> searchCountriesStartingWith(String alphabet) {
        return countryRepository.findByNameStartingWithIgnoreCase(alphabet);
    }
}