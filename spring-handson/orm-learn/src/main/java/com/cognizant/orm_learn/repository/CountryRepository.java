package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Search by partial name
    List<Country> findByNameContainingIgnoreCase(String text);

    // Search by partial name in ascending order
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String text);

    // Search by starting letter
    List<Country> findByNameStartingWithIgnoreCase(String alphabet);

}