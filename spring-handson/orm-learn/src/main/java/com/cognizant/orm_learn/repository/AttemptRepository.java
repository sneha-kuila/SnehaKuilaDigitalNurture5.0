package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query("""
            SELECT DISTINCT a
            FROM Attempt a
            JOIN FETCH a.user
            JOIN FETCH a.attemptQuestions aq
            JOIN FETCH aq.question
            """)
    List<Attempt> getAttemptDetail();

    @Query(value = "SELECT * FROM attempt", nativeQuery = true)
    List<Attempt> getAllAttemptsNative();
}