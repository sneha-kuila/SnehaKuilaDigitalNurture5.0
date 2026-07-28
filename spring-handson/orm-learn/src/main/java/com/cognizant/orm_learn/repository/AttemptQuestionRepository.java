package com.cognizant.orm_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.AttemptQuestion;

@Repository
public interface AttemptQuestionRepository extends JpaRepository<AttemptQuestion, Integer> {

}
